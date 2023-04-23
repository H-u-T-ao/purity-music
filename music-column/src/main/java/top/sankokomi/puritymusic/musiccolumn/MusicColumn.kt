package top.sankokomi.puritymusic.musiccolumn

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import top.sankokomi.puritymusic.basis.app.Api
import top.sankokomi.puritymusic.basis.app.Global
import top.sankokomi.puritymusic.basis.net.request
import top.sankokomi.puritymusic.basis.tool.showToast
import top.sankokomi.puritymusic.musiccolumn.entity.Song
import top.sankokomi.puritymusic.musiccolumn.net.MusicDetailApi
import top.sankokomi.puritymusic.basis.R as BR

class MusicColumn @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private val musics = mutableListOf<Song>()

    private val adapter = MusicColumnAdapter(musics)

    private val lock: Mutex = Mutex(locked = false)

    /**
     * 连接 [Context] ，初始化 [RecyclerView.LayoutManager] 和 [RecyclerView.Adapter]
     *
     * @param context 上下文，如果选择传入 [layoutManager] 则可以不传
     * @param layoutManager 布局管理器，可以只传入 [context] ，若只传入 [context]
     * 则默认使用纵向的 [LinearLayoutManager]
     * @param
     * */
    fun attach(
        context: Context? = null,
        layoutManager: LayoutManager = LinearLayoutManager(context)
    ) = apply {
        setLayoutManager(layoutManager)
        setAdapter(adapter)
    }

    /**
     * 重置整个 [MusicColumn] ，即清除原来的再添加新的，若传入空列表，则只清除而不添加
     *
     * @param musicIds 要添加的新的歌曲 ID 列表
     * */
    suspend fun setMusicList(musicIds: List<Long>) = apply {
        lock.withLock {
            withContext(Dispatchers.Main) {
                val size = musics.size
                musics.clear()
                adapter.notifyItemRangeRemoved(0, size)
                addMusicListUnsafely(musicIds)
            }
        }
    }

    /**
     * 将新的歌曲添加到列表，即不清除原来的，直接在后面添加新的
     *
     * @param musicIds 要添加的新的歌曲 ID 列表
     * */
    suspend fun addMusicList(musicIds: List<Long>) = apply {
        lock.withLock {
            withContext(Dispatchers.Main) {
                addMusicListUnsafely(musicIds)
            }
        }
    }

    private suspend fun addMusicListUnsafely(musicIds: List<Long>) {
        if (musicIds.isEmpty()) return
        val ids = musicIds.joinToString(separator = ",")
        Api<MusicDetailApi>().request {
            musicDetail(ids)
        } response {
            success {
                it.songs ?: return@success
                val size = musics.size
                musics.addAll(it.songs)
                adapter.notifyItemRangeInserted(size, it.songs.size)
            }
            failure {
                showToast(Global.app.getString(BR.string.toast_network_error_normal))
            }
        }
    }

    class MusicColumnAdapter(
        private val musics: List<Song>
    ) : Adapter<MusicDetailItemHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicDetailItemHolder {
            return MusicDetailItemHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_music_detail, parent, false)
            )
        }

        override fun getItemCount(): Int = musics.size

        override fun onBindViewHolder(holder: MusicDetailItemHolder, position: Int) {
            holder.bindMusicDetailItem(musics[position])
        }

    }

    class MusicDetailItemHolder(itemView: View) : ViewHolder(itemView) {

        private val name: TextView = itemView.findViewById(R.id.tv_music_item_name)

        private val artists: TextView = itemView.findViewById(R.id.tv_music_item_artists)

        private val album: TextView = itemView.findViewById(R.id.tv_music_item_album)

        private var _musicDetail: Song? = null
        val musicDetail: Song get() = _musicDetail!!

        fun bindMusicDetailItem(music: Song) {
            _musicDetail = music
            name.text = music.name
            val ars = music.ar?.map { it.name ?: "" }.let {
                it?.joinToString(separator = "/") ?: ""
            }
            artists.text = ars
            album.text = music.al?.name ?: ""
        }
    }

}