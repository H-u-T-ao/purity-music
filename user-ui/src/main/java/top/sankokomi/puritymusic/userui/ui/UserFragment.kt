package top.sankokomi.puritymusic.userui.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import top.sankokomi.puritymusic.basis.app.Api
import top.sankokomi.puritymusic.basis.net.request
import top.sankokomi.puritymusic.basis.ui.BasicFragment
import top.sankokomi.puritymusic.userui.net.UserApi

class UserFragment : BasicFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifecycleScope.launch {
            // 获取验证码
            Api<UserApi>().request {
                captchaSent(1892786/* 电话号码 */)
            } response {
                success {
                    println("请求成功")
                }
                failure {
                    println("请求失败")
                }
            }
        }
    }

}