package top.sankokomi.puritymusic.basis.app

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import top.sankokomi.puritymusic.basis.entity.UserPub
import top.sankokomi.puritymusic.basis.net.MusicApi
import top.sankokomi.puritymusic.basis.net.SetCookiesInterceptor

/**
 * 全局类，存放一些全局变量
 * */
@Suppress("StaticFieldLeak")
object Global {

    /**
     * Music Api 的 URL
     * */
    private const val MusicApiHost = "https://sankokomi.top:22222/"

    /**
     * 应用的 [android.app.Application]
     * */
    lateinit var app: Context
        internal set

    private val globals = hashMapOf<String, Any?>()

    /**
     * 安装一个全局变量
     *
     * @see [uninstall]
     * @see [get]
     * */
    fun install(key: String, value: Any?) {
        globals[key] = value
    }

    /**
     * 卸载一个全局变量
     *
     * @see [install]
     * @see [get]
     * */
    fun uninstall(key: String): Any? = globals.remove(key)

    /**
     * 获取一个已安装的全局变量，若不存在此变量或此变量为空，则返回空，否则获取变量并转换类型为 [T]
     *
     * @see [install]
     * @see [uninstall]
     * */
    @Suppress("UNCHECKED_CAST")
    fun <T> get(key: String): T? = globals[key].let {
        if (it != null) it as T else null
    }

    /**
     * [GlobalScope]
     *
     * 使用时请注意内存泄漏问题
     * */
    @OptIn(DelicateCoroutinesApi::class)
    val coroutineScope: CoroutineScope = GlobalScope

    private val okHttpClient: OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(SetCookiesInterceptor())
            .build()

    internal val retrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl(MusicApiHost)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    /**
     * API 商店，在这里注册和注销 [Retrofit] 动态代理接口
     * */
    @PublishedApi
    internal val apiStore: ApiStore = ApiStore()

    private val _user: MutableStateFlow<UserPub?> = MutableStateFlow(null)

    /**
     * 用户基本信息
     *
     * 可能为空，若为空，则说明未登录
     * */
    val user: StateFlow<UserPub?> = _user.asStateFlow()

    /**
     * 用户登录
     *
     * @param u 登录的用户信息
     * */
    fun userLogin(u: UserPub) {
        _user.value = u
    }

    /**
     * 退出登录
     * */
    fun userLogout() {
        _user.value = null
    }

}

@Suppress("FunctionName")
inline fun <reified API : MusicApi> Api(): API {
    return Global.apiStore.registerApi(API::class.java)
}