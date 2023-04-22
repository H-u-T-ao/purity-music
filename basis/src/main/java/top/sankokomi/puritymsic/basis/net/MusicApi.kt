package top.sankokomi.puritymsic.basis.net

import android.os.SystemClock
import okhttp3.ResponseBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
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
interface MusicApi {

    @GET("cloudsearch")
    suspend fun cloudSearch(
        @Query("keywords") key: String,
        @Query("timestamp") timestamp: Long = SystemClock.uptimeMillis()
    ): ResponseBody

    /**
     * 发送验证码，不要老是请求，防止风控
     *
     * @param phone 手机号码
     * @param ctCode 区号，默认 86
     * */
    @POST("captcha/sent")
    @FormUrlEncoded
    suspend fun captchaSent(
        @Field("phone") phone: Long,
        @Field("ctcode") ctCode: Int = 86,
        @Query("timestamp") timestamp: Long = SystemClock.uptimeMillis()
    ): SimpleResponse

    /**
     * 验证验证码，登陆账号
     *
     * @param phone 手机号码
     * @param captcha 验证码
     * @param ctCode 区号，默认 86
     * */
    @POST("captcha/verify")
    @FormUrlEncoded
    suspend fun captchaVerify(
        @Field("phone") phone: Long,
        @Field("captcha") captcha: Int,
        @Field("ctcode") ctCode: Int = 86,
        @Query("timestamp") timestamp: Long = SystemClock.uptimeMillis()
    ): SimpleResponse

}