package com.example.flo

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.flo.databinding.ActivitySongBinding
import java.util.*

class SongActivity : AppCompatActivity() {

    lateinit var binding: ActivitySongBinding
    lateinit var timer: Timer

    private val song: Song = Song()

    private var mediaPlayer: MediaPlayer? = null

    private lateinit var player: Player
    //private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSong()

        player = Player(song.playTime, song.isPlaying)
        player.start()

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

        binding.songRepeatInactiveOnOneIv.setOnClickListener {
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

        binding.songAlbumExp2Iv.clipToOutline = true

    }

    private fun initSong() {
        if (intent.hasExtra("title") && intent.hasExtra("singer") && intent.hasExtra("playTime")
            && intent.hasExtra("isPlaying")
        ) {
            song.title = intent.getStringExtra("title")!!
            song.singer = intent.getStringExtra("singer")!!
            song.second = intent.getIntExtra("second",0)
            song.playTime = intent.getIntExtra("playTime", 0)
            song.isPlaying = intent.getBooleanExtra("isPlaying", false)
            song.music

            binding.songEndTimeTv.text = String.format("%02d:%02d", song.playTime / 60, song.playTime % 60)
            binding.songTitleTv.text = song.title
            binding.songNameTv.text = song.singer
            setPlayerStatus(song.isPlaying)
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
                binding.songRepeatInactiveOnOneIv.visibility = View.VISIBLE
            }
            3 -> {
                binding.songRepeatInactiveOnOneIv.visibility = View.GONE
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
                    if (second >= playTime) {
                        break
                    }

                    if (isPlaying) {
                        sleep(1000)
                        second++

                        runOnUiThread {
                            binding.songPlayerSb.progress = second * 1000 / playTime
                            binding.songStartTimeTv.text = String.format("%02d:%02d", second/60, second%60)
                        }
                    }
                }

            } catch (e: InterruptedException) {
                Log.d("interrupt", "쓰레드가 종료되었습니다.")
            }


        }
    }

    override fun onDestroy() {
        super.onDestroy()
        player.interrupt() // 스레드 해제
        mediaPlayer?.release() // 미디어 플레이어가 갖고 있던 리소스 해제
        mediaPlayer = null // 미디어 플레이어 해제
    }

}
