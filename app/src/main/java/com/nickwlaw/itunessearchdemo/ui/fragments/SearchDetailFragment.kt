package com.nickwlaw.itunessearchdemo.ui.fragments

import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.nickwlaw.itunessearchdemo.BR
import com.nickwlaw.itunessearchdemo.databinding.FragmentSearchDetailBinding
import com.nickwlaw.itunessearchdemo.foundation.BaseFragment
import com.nickwlaw.itunessearchdemo.ui.viewmodels.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.io.IOException

class SearchDetailFragment : BaseFragment<FragmentSearchDetailBinding>() {

    private val viewModel: SearchViewModel by sharedViewModel()
    private lateinit var mediaPlayer: MediaPlayer

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchDetailBinding =
        FragmentSearchDetailBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            song = this@SearchDetailFragment.viewModel.selectedSongLiveData.value
            setVariable(BR.song, viewModel.selectedSongLiveData.value)
        }

        binding.ivArt.setOnClickListener {
            setupMediaPlayer()
        }

        binding.llItunesLinkContainer.setOnClickListener {
            val url = viewModel.selectedSongLiveData.value?.iTunesUrl
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer.release()
    }

    private fun setupMediaPlayer() {
        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            try {
                setDataSource(viewModel.selectedSongLiveData.value?.previewUrl)
                prepare()
                start()
            } catch (ioe: IOException) {
                viewModel.onError(ioe)
            }
        }
    }
}