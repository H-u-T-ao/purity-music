package top.sankokomi.puritymusic.musiccolumn.musiclist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import coil.load
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import top.sankokomi.puritymusic.basis.ui.BasicFragment
import top.sankokomi.puritymusic.musiccolumn.R
import top.sankokomi.puritymusic.musiccolumn.databinding.MusicListDescriptionFragmentBinding

class MusicListDescriptionFragment : BasicFragment() {

    private lateinit var binding: MusicListDescriptionFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.music_list_description_fragment,
            container,
            false
        )
        val bundle = arguments
        binding.ivMusicListDescriptionTop.load(bundle?.getString("titlePhoto"))
        binding.tvMusicListDescriptionTop.text = bundle?.getString("title")
        binding.tvMusicListDescriptionText.text = bundle?.getString("titleDescription")
        return binding.root
    }

}