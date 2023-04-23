package top.sankokomi.puritymusic.basis.app

import retrofit2.Retrofit
import top.sankokomi.puritymusic.basis.net.MusicApi

class ApiStore {

    private val musicApis = hashMapOf<String, MusicApi>()

    /**
     * 注册一个基于 [Retrofit] 动态代理服务的 API 接口
     *
     * 如果此前已经创建过，不会重复创建，而是返回此前创建的动态代理对象
     *
     * @param api 要注册的接口
     * @return 注册好的接口
     *
     * @see unregisterApi
     * */
    fun <API : MusicApi> registerApi(api: Class<API>): API {
        var musicApi = musicApis[api.name]
        @Suppress("UNCHECKED_CAST")
        if (musicApi != null) return musicApi as API
        musicApi = Global.retrofit.create(api)
        musicApis[api.name] = musicApi
        return musicApi
    }

    /**
     * 注销一个 API 接口
     *
     * @param api 要注销的接口
     * @return 若此接口原本存在，则移除并返回此接口的动态代理类，否则返回空
     *
     * @see registerApi
     * */
    fun <API : MusicApi> unregisterApi(api: Class<API>): Any? = musicApis.remove(api.name)


}