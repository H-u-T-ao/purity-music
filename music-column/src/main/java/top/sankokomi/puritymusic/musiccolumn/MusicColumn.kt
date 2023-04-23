package top.sankokomi.puritymusic.musiccolumn

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MainThread
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import top.sankokomi.puritymusic.musiccolumn.entity.MusicDetail

class MusicColumn @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private val ids = mutableListOf<Long>()

    private val details = mutableListOf<MusicDetail>()

    private val adapter = MusicColumnAdapter(details)

    suspend fun resetMusicList(musicIds: List<Long>) {
        withContext(Dispatchers.Main) {
            ids.clear()
            ids.addAll(musicIds)
            val size = details.size
            details.clear()
            adapter.notifyItemRangeRemoved(0, size)
        }
        withContext(Dispatchers.IO) {

        }
    }

    suspend fun addMusicList(musicIds: List<Long>) {
        withContext(Dispatchers.Main) {
            ids.addAll(musicIds)
        }
        withContext(Dispatchers.IO) {

        }
    }

    class MusicColumnAdapter(
        private val details: List<MusicDetail>
    ) : Adapter<MusicDetailItemHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicDetailItemHolder {
            return MusicDetailItemHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_music_detail, parent, false)
            )
        }

        override fun getItemCount(): Int = details.size

        override fun onBindViewHolder(holder: MusicDetailItemHolder, position: Int) {
        }

    }

    class MusicDetailItemHolder(itemView: View) : ViewHolder(itemView)

}