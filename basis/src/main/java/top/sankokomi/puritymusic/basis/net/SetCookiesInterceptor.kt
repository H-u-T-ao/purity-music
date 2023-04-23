package top.sankokomi.puritymusic.basis.net

import okhttp3.Interceptor
import okhttp3.Response

class SetCookiesInterceptor : Interceptor {

    private var cookies: String? = null

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
    }

}