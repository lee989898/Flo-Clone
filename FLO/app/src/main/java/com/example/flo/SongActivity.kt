package com.example.flo

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.flo.databinding.ActivitySongBinding
import com.google.gson.Gson
import java.util.*

class SongActivity : AppCompatActivity() {

    lateinit var binding: ActivitySongBinding
    private lateinit var player: Player

    private val song: Song = Song()

    private var mediaPlayer: MediaPlayer? = null

    private var gson: Gson = Gson()


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
            setPlayerStatus(true)
            player.isPlaying = true
            song.isPlaying = true
            mediaPlayer?.start()
        }

        binding.songPauseIv.setOnClickListener {
            setPlayerStatus(false)
            player.isPlaying = false
            song.isPlaying = false
            mediaPlayer?.pause()
        }

        binding.songRepeatOffIv.setOnClickListener {
            setRepeatStatus(1)
        }

        binding.songRepeatAllIv.setOnClickListener {
            setRepeatStatus(2)
        }

        binding.songRepeatOneIv.setOnClickListener {
            setRepeatStatus(3)
        }

        binding.songRandomOffIv.setOnClickListener {
            setRandomStatus(false)
        }

        binding.songRandomOnIv.setOnClickListener {
            setRandomStatus(true)
        }

        binding.songAlbumIv.clipToOutline = true

    }

    private fun initSong() {
        if (intent.hasExtra("title") && intent.hasExtra("singer") && intent.hasExtra("second")&& intent.hasExtra("playTime")
            && intent.hasExtra("isPlaying") && intent.hasExtra("music")
        ) {
            song.title = intent.getStringExtra("title")!!
            song.singer = intent.getStringExtra("singer")!!
            song.second = intent.getIntExtra("second",0)
            song.playTime = intent.getIntExtra("playTime", 0)
            song.isPlaying = intent.getBooleanExtra("isPlaying", false)
            song.music = intent.getStringExtra("music")!!
            val music = resources.getIdentifier(song.music, "raw", this.packageName)

            binding.songEndTimeTv.text = String.format("%02d:%02d", song.playTime / 60, song.playTime % 60)
            binding.songMusicTitleTv.text = song.title
            binding.songSingerNameTv.text = song.singer
            setPlayerStatus(song.isPlaying)
            mediaPlayer = MediaPlayer.create(this, music)
        }
    }

    private fun setPlayerStatus(isPlaying: Boolean) {
        if (isPlaying) {
            binding.songMiniplayerIv.visibility = View.GONE
            binding.songPauseIv.visibility = View.VISIBLE
        } else if (!(isPlaying)) {
            binding.songMiniplayerIv.visibility = View.VISIBLE
            binding.songPauseIv.visibility = View.GONE
        }
    }

    private fun setRepeatStatus(repeatWhat: Int) {
        when (repeatWhat) {
            1 -> {
                binding.songRepeatOffIv.visibility = View.GONE
                binding.songRepeatAllIv.visibility = View.VISIBLE

            }
            2 -> {
                binding.songRepeatAllIv.visibility = View.GONE
                binding.songRepeatOneIv.visibility = View.VISIBLE
            }
            else -> {
                binding.songRepeatOneIv.visibility = View.GONE
                binding.songRepeatOffIv.visibility = View.VISIBLE
            }
        }
    }

    private fun setRandomStatus(isPlaying: Boolean) {
        if (isPlaying) {
            binding.songRandomOffIv.visibility = View.VISIBLE
            binding.songRandomOnIv.visibility = View.GONE
        } else {
            binding.songRandomOffIv.visibility = View.GONE
            binding.songRandomOnIv.visibility = View.VISIBLE
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
                            binding.musicplayerProgressSb.progress = second * 1000 / playTime
                            binding.songStartTimeTv.text = String.format("%02d:%02d", second/60, second%60)
                        }
                    }
                }

            } catch (e: InterruptedException) {
                Log.d("interrupt", "쓰레드가 종료되었습니다.")
            }


        }
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.pause() // 미디어 플레이어 중지
        player.isPlaying = false // 스레드 중지
        song.isPlaying = false
        song.second = (binding.musicplayerProgressSb.progress*song.playTime)/1000
        setPlayerStatus(false) // 정지상태일 때의 이미지로 전환

        val sharedPreferences = getSharedPreferences("song", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val json = gson.toJson(song)
        editor.putString("song", json)

        editor.apply()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.interrupt() // 스레드 해제
        mediaPlayer?.release() // 미디어 플레이어가 갖고 있던 리소스 해제
        mediaPlayer = null // 미디어 플레이어 해제
    }

}
