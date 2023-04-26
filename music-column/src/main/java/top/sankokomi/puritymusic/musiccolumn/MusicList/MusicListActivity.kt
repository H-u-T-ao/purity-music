package top.sankokomi.puritymusic.musiccolumn.MusicList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import top.sankokomi.puritymusic.musiccolumn.MusicColumn
import top.sankokomi.puritymusic.musiccolumn.R

class MusicListActivity : AppCompatActivity() {

    private val mMusicListId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_list)
        lifecycleScope.launch {
            findViewById<MusicColumn>(R.id.musiccolumn_music_list)
                .attach(this@MusicListActivity)
                .addMusicList(
                    mutableListOf(
                        347230L,
                        347231L,
                        405998841,
                        33894312,
                        1357375695,
                        400162138,
                        1357374736,
                        469391964
                    )
                )
        }
    }

}