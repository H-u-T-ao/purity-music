package top.sankokomi.puritymsic.basis.app

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
import retrofit2.create
import top.sankokomi.puritymsic.basis.net.MusicApi
import top.sankokomi.puritymsic.basis.net.OkHttpCookiesInterceptor
import top.sankokomi.puritymsic.basis.user.User

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

    /**
     * [GlobalScope]
     *
     * 使用时请注意内存泄漏问题
     * */
    @OptIn(DelicateCoroutinesApi::class)
    val coroutineScope: CoroutineScope = GlobalScope

    private val okHttpClient: OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(OkHttpCookiesInterceptor())
            .build()

    private val retrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl(MusicApiHost)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    /**
     * [MusicApi]
     * */
    internal val musicApi: MusicApi = retrofit.create()

    private val _user: MutableStateFlow<User?> = MutableStateFlow(null)

    /**
     * 用户基本信息
     *
     * 可能为空，若为空，则说明未登录
     * */
    val user: StateFlow<User?> = _user.asStateFlow()

    /**
     * 用户登录
     *
     * @param u 登录的用户信息
     * */
    fun userLogin(u: User) {
        _user.value = u
    }

    /**
     * 退出登录
     * */
    fun userLogout() {
        _user.value = null
    }

}