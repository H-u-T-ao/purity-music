package top.sankokomi.puritymusic.basis.net

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import top.sankokomi.puritymusic.basis.app.Global
import kotlin.coroutines.CoroutineContext

/**
 * 封装好的网络请求函数，用法如下：
 *
 *     coroutineScope.launch {
 *         Api<CloudSearchApi>().request { cloudSearch("黄霄雲") } response {
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
 *         Api<CloudSearchApi>().request { cloudSearch("赖美云") } response {
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
 * @return 一个 [ApiFlow] 对象，直接执行 [ApiFlow.response] 即可开始发起请求
 * */
suspend inline fun <reified API : MusicApi, T> API.request(
    context: CoroutineContext = Dispatchers.IO,
    crossinline request: suspend API.() -> T
): ApiFlow<T> =
    ApiFlow(
        context,
        flow { emit(Global.apiStore.registerApi(API::class.java).request()) }
    )

class ApiFlow<T>
@PublishedApi internal constructor(
    private val context: CoroutineContext,
    private val apiFlow: Flow<T>
) {

    /**
     * 请求并收集在请求的接口上返回的数据，若请求成功，则在 [success]
     * 中返回，若请求失败抛出异常，则将在 [failure] 中返回
     * */
    suspend infix fun response(
        handle: ApiFlow<T>.() -> Unit
    ) {
        apply(handle)
        apiFlow.catch { failure(it) }.flowOn(context).collect { success(it) }
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