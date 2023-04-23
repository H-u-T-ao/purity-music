package top.sankokomi.puritymusic

import android.os.Bundle
import top.sankokomi.puritymusic.basis.ui.BasicActivity

class MainActivity : BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}