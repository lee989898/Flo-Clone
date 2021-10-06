package com.example.flo

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.flo.databinding.ActivitySongBinding



class SongActivity : AppCompatActivity() {


    lateinit var binding: ActivitySongBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)





        if (intent.hasExtra("title") && intent.hasExtra("singer")) {
            binding.songTitleTv.text = intent.getStringExtra("title")
            binding.songNameTv.text = intent.getStringExtra("singer")
        }

        binding.songAlbumExp2Iv.clipToOutline = true

        binding.songDownIb.setOnClickListener {
            finish()
        }

        binding.songMiniplayerIv.setOnClickListener {
            setPlayerStatus(false)
        }

        binding.songPauseIv.setOnClickListener {
            setPlayerStatus(true)
        }

        binding.songRepeatInactiveOffIv.setOnClickListener {
            setRepeatStatus(1)
        }

        binding.songRepeatInactiveOnIv.setOnClickListener {
            setRepeatStatus(2)
        }

        binding.songRepeatInactiveOn1Iv.setOnClickListener {
            setRepeatStatus(3)
        }

        binding.songRepeatPlaylistIv.setOnClickListener {
            setRepeatStatus(4)
        }

        binding.songRandomOffIb.setOnClickListener {
            setRandomStatus(false)
        }

        binding.songRandomOnIb.setOnClickListener {
            setRandomStatus(true)
        }


    }

    public fun setPlayerStatus(isPlaying: Boolean) {
        if (isPlaying) {
            binding.songMiniplayerIv.visibility = View.VISIBLE
            binding.songPauseIv.visibility = View.GONE
        } else {
            binding.songMiniplayerIv.visibility = View.GONE
            binding.songPauseIv.visibility = View.VISIBLE
        }
    }

    private fun setRepeatStatus(repeatWhat: Int) {
        when (repeatWhat) {
            1 -> {
                binding.songRepeatInactiveOffIv.visibility = View.GONE
                binding.songRepeatInactiveOnIv.visibility = View.VISIBLE

            }
            2 -> {
                binding.songRepeatInactiveOnIv.visibility = View.GONE
                binding.songRepeatInactiveOn1Iv.visibility = View.VISIBLE
            }
            3 -> {
                binding.songRepeatInactiveOn1Iv.visibility = View.GONE
                binding.songRepeatPlaylistIv.visibility = View.VISIBLE
            }
            else -> {
                binding.songRepeatInactiveOffIv.visibility = View.VISIBLE
                binding.songRepeatPlaylistIv.visibility = View.GONE
            }


        }

    }

    private fun setRandomStatus(isPlaying: Boolean) {
        if (isPlaying) {
            binding.songRandomOffIb.visibility = View.VISIBLE
            binding.songRandomOnIb.visibility = View.GONE
        } else {
            binding.songRandomOffIb.visibility = View.GONE
            binding.songRandomOnIb.visibility = View.VISIBLE
        }
    }


}