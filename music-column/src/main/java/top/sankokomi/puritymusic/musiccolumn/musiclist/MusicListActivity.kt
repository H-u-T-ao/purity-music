package top.sankokomi.puritymusic.musiccolumn.musiclist

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.lifecycleScope
import androidx.palette.graphics.Palette
import coil.imageLoader
import coil.load
import coil.request.ImageRequest
import coil.request.SuccessResult
import coil.size.Scale
import coil.size.Size
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import top.sankokomi.puritymusic.basis.app.Api
import top.sankokomi.puritymusic.basis.app.Global.coroutineScope
import top.sankokomi.puritymusic.basis.net.request
import top.sankokomi.puritymusic.basis.ui.BasicActivity
import top.sankokomi.puritymusic.musiccolumn.MusicColumn
import top.sankokomi.puritymusic.musiccolumn.R
import top.sankokomi.puritymusic.musiccolumn.databinding.ActivityMusicListBinding
import top.sankokomi.puritymusic.musiccolumn.net.MusicDetailApi


class MusicListActivity : BasicActivity() {

    private val mMusicListId: Int = 503337470

    private lateinit var binding: ActivityMusicListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_list)
        binding = DataBindingUtil.setContentView<ActivityMusicListBinding>(
            this,
            R.layout.activity_music_list
        )
        coroutineScope.launch {
            Api<MusicDetailApi>().request { playListDetail(mMusicListId) } response {
                success {
                    runBlocking<Unit> {
                        launch(Dispatchers.IO) { // 在 IO 线程中启动一个协程
                            launch {
                                Palette.from(getImageBitmapByUrl(it.playlist.coverImgUrl))
                                    .generate { palette ->
                                        val color = palette?.getDominantColor(
                                            ContextCompat.getColor(
                                                this@MusicListActivity,
                                                top.sankokomi.puritymusic.basis.R.color.gray
                                            )
                                        )
                                        // 在这里使用提取出来的颜色进行相应的操作
                                    }
                            }
                            withContext(Dispatchers.Main) { // 切换到主线程
                                binding.ivMusicListCover.load(it.playlist.coverImgUrl) {
                                    scale(Scale.FILL)
                                    Size(50, 50)
                                }
                                binding.ivMusicListCreator.load(it.playlist.creator.avatarUrl) {
                                    scale(Scale.FILL)
                                }
                                binding.tvMusicListCreator.text = it.playlist.creator.nickname
                                binding.tvMusicListShare.text = it.playlist.shareCount.toString()
                                binding.tvMusicListChat.text = it.playlist.commentCount.toString()
                                binding.tvMusicListCollect.text =
                                    it.playlist.subscribedCount.toString()
                                binding.tvMusicListTitle.text = it.playlist.name
                                binding.tvMusicListDescription.text = it.playlist.description

                            }
                        }
                    }
                    binding.ivMusicListCover.setOnClickListener { click ->
                        // 创建 FragmentManager 对象
                        val fragmentManager: FragmentManager = supportFragmentManager
                        // 开始一个新的事务
                        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
                        // 创建一个新的 Fragment 实例并添加到容器中
                        val myFragment = MusicListDescriptionFragment()
                        //创建资源绑定器bundle
                        val bundle = Bundle()
                        //添加数据到资源绑定器
                        bundle.putString("titlePhoto", it.playlist.coverImgUrl)
                        bundle.putString("title", it.playlist.name)
                        bundle.putString("titleDescription", it.playlist.description)
                        //将资源绑定器添加到fragment对象中
                        myFragment.arguments = bundle
                        transaction.replace(R.id.dd, myFragment)
                        //返回键事件
                        transaction.addToBackStack(null);
                        // 提交事务
                        transaction.commit()
                    }
                }
            }
        }
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

    override fun onResume() {
        super.onResume()
    }


    suspend fun Context.getImageBitmapByUrl(url: String): Bitmap {
        val request = ImageRequest.Builder(this)
            .data(url)
            .allowHardware(false)
            .build()
        val result = (imageLoader.execute(request) as SuccessResult).drawable
        return (result as BitmapDrawable).bitmap
    }


}