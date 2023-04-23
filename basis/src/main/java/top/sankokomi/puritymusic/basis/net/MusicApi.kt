package top.sankokomi.puritymusic.basis.net

import retrofit2.Retrofit
import com.google.gson.Gson

/**
 * [Retrofit] 的服务，使用了协程，支持 [Gson]
 *
 * 请求时必加一个参数
 *
 *     @Query("timestamp") timestamp: Long = SystemClock.uptimeMillis()
 *
 * 以防止请求被服务器缓存
 * */
interface MusicApi