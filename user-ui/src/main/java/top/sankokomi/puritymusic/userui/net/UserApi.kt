package top.sankokomi.puritymusic.userui.net

import android.os.SystemClock
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query
import top.sankokomi.puritymusic.basis.net.MusicApi
import top.sankokomi.puritymusic.basis.net.SimpleResponse

interface UserApi : MusicApi {

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