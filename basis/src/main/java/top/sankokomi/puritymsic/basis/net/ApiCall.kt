package top.sankokomi.puritymsic.basis.net

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import top.sankokomi.puritymsic.basis.app.Global
import kotlin.coroutines.CoroutineContext

/**
 * 封装好的网络请求函数，用法如下：
 *
 *     coroutineScope.launch {
 *         apiFlow { cloudSearch("黄霄雲") } collect {
 *             success {
 *                 // 可选参数，可以不设置，请求成功时回调
 *             }
 *             failure {
 *                 // 可选参数，可以不设置，请求失败时回调
 *             }
 *         }
 *     }
 *
 *
 * 若仅关注请求成功事件，可以写成如下形式：
 *
 *     coroutineScope.launch {
 *         apiFlow { cloudSearch("赖美云") } collect {
 *             success {
 *                 // 请求成功时回调
 *             }
 *         }
 *     }
 *
 * 无须担心上述用法会抛出异常，如果有异常会自动捕获，若同时配置了请求失败回调，则回调，否则直接**抛弃**异常
 *
 * @param context 在指定的协程上下文中执行请求，默认为 [Dispatchers.IO]
 * @param request 要执行的请求，一般是 [MusicApi] 中的函数
 * @return 一个 [ApiFlow] 对象，直接执行 [ApiFlow.collect] 即可开始发起请求
 * */
suspend fun <T> apiFlow(
    context: CoroutineContext = Dispatchers.IO,
    request: suspend MusicApi.() -> T
): ApiFlow<T> =
    ApiFlow(
        context,
        flow { emit(Global.musicApi.request()) }
    )

class ApiFlow<T> internal constructor(
    private val context: CoroutineContext,
    private val apiFlow: Flow<T>
) {

    /**
     * 请求并收集在请求的接口上返回的数据，若请求成功，则在 [success]
     * 中返回，若请求失败抛出异常，则将在 [failure] 中返回
     * */
    suspend infix fun collect(
        handle: ApiFlow<T>.() -> Unit
    ) {
        apply(handle)
        withContext(context) {
            apiFlow.catch { failure(it) }.collect { success(it) }
        }
    }

    private var success: (T) -> Unit = {}

    private var failure: (Throwable) -> Unit = {}

    /**
     * 配置请求成功时回调的函数
     * */
    fun success(handle: (T) -> Unit) {
        success = handle
    }

    /**
     * 配置请求失败时回调的函数
     * */
    fun failure(handle: (Throwable) -> Unit) {
        failure = handle
    }

}