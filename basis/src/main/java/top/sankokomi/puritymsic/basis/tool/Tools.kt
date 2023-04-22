package top.sankokomi.puritymsic.basis.tool

import android.content.Context
import android.content.Intent
import android.os.Looper
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import top.sankokomi.puritymsic.basis.app.Global
import top.sankokomi.puritymsic.basis.ui.BasicActivity
import top.sankokomi.puritymsic.basis.ui.BasicService

private var toast: Toast? = null

/**
 * Toast 通知，自动切主线程，自动取消上一个显示最新的，使用 [Global.app] 作为 [Context]
 * */
fun showToast(msg: String, time: Int = Toast.LENGTH_LONG) {
    if (Looper.getMainLooper() == Looper.myLooper()) {
        showToastInternal(msg, time)
    } else {
        Global.coroutineScope.launch(Dispatchers.Main) {
            showToastInternal(msg, time)
        }
    }
}

private fun showToastInternal(msg: String, time: Int) {
    toast?.cancel()
    toast = null
    toast = Toast.makeText(Global.app, msg, time).apply(Toast::show)
}

/**
 * 启动 [BasicActivity]
 * */
inline fun <reified A : BasicActivity> Context.startActivity(intent: Intent.() -> Unit) {
    startActivity(Intent(this, A::class.java).apply(intent))
}

/**
 * 启动 [BasicService]
 * */
inline fun <reified S : BasicService> Context.startService(intent: Intent.() -> Unit) {
    startService(Intent(this, S::class.java).apply(intent))
}

/**
 * 新建一个 [VM] 类型的 [ViewModel]
 *
 * @return 新建好的 [VM] 类型的 [ViewModel]
 * */
inline fun <reified VM : ViewModel> ViewModelStoreOwner.viewModel(): VM {
    return ViewModelProvider(this)[VM::class.java]
}

/**
 * 根据配置的 [factory] 和 [defaultCreationExtras] 新建一个 [VM] 类型的 [ViewModel]
 *
 * @return 新建好的 [VM] 类型的 [ViewModel]
 * */
inline fun <reified VM : ViewModel> ViewModelStoreOwner.viewModel(
    factory: ViewModelProvider.Factory,
    defaultCreationExtras: CreationExtras
): VM {
    return ViewModelProvider(
        viewModelStore,
        factory,
        defaultCreationExtras
    )[VM::class.java]
}
