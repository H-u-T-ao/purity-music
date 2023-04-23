package top.sankokomi.puritymusic.musiccolumn

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import top.sankokomi.puritymusic.basis.ui.BasicActivity

class MusicColumnUI : BasicActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ui_music_column)
        lifecycleScope.launch {
            findViewById<MusicColumn>(R.id.mc_music_column_column)
                .attach(this@MusicColumnUI)
                .addMusicList(mutableListOf(347230L, 347231L))
        }
    }

}