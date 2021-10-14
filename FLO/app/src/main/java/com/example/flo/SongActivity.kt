package com.example.flo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.flo.databinding.ActivitySongBinding



class SongActivity : AppCompatActivity() {


    lateinit var binding: ActivitySongBinding

    private lateinit var player: Player

//    private val handler = Handler(Looper.getMainLooper())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var song = Song()

        player = Player(song.playTime, song.isPlaying)
        player.start()

        if (intent.hasExtra("title") && intent.hasExtra("singer")) {
            binding.songTitleTv.text = intent.getStringExtra("title")
            binding.songNameTv.text = intent.getStringExtra("singer")

            var firstNum = intent.getIntExtra("isplay", 0)

            if (firstNum == 1) {
                setPlayerStatus(false)
            } else if (firstNum == 2) {
                setPlayerStatus(true)
            }
        }

        binding.songAlbumExp2Iv.clipToOutline = true

        binding.songDownIb.setOnClickListener {


            finish()
        }

        binding.songMiniplayerIv.setOnClickListener {
            player.isPlaying = true
            setPlayerStatus(false)
        }

        binding.songPauseIv.setOnClickListener {
            player.isPlaying = false
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

    private fun setPlayerStatus(isPlaying: Boolean) {
        if (isPlaying) {
            binding.songMiniplayerIv.visibility = View.VISIBLE
            binding.songPauseIv.visibility = View.GONE
        } else if (!(isPlaying)) {
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

    inner class Player(private val playTime: Int, var isPlaying: Boolean) : Thread() {
        private var second = 0

        override fun run() {

            try {

                while (true) {
                    if (isPlaying) {
                        sleep(1000)
                        second++

                        runOnUiThread {
                            binding.songSeekbarSb.progress = second * 1000 / playTime
                            binding.songStartTimeTv.text =
                                String.format("%02:%02d", second / 60, second % 60)
                        }

                    }
                }

                if (second >= playTime) {
                    break
                }
            } catch (e: InterruptedException) {
                Log.d("interrupt", "쓰레드가 종료되었습니다.")
            }

        }

        override fun onDestroy() {
            player.interrupt()
            super.onDestroy()
        }

    }
}