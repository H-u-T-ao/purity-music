package top.sankokomi.puritymusic

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import top.sankokomi.puritymsic.basis.app.Global
import top.sankokomi.puritymsic.basis.ui.BasicActivity

class MainActivity : BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}