package com.example.flo

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.flo.databinding.ActivityMainBinding
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private var gson : Gson = Gson()

    private var song: Song = Song()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavigation()
        inputDumpySongs()

       val song = Song("라일락", "아이유(IU)",0, 215,false,"music_lilac")
       setMiniPlayer(song)

        binding.mainPlayerLayout.setOnClickListener {
            val intent = Intent(this, SongActivity::class.java)
            intent.putExtra("title", song.title)
            intent.putExtra("singer", song.singer)
            intent.putExtra("second", song.second)
            intent.putExtra("playTime", song.playTime)
            intent.putExtra("isPlaying", song.isPlaying)
            intent.putExtra("music", song.music)
            startActivity(intent)
        }

        binding.mainMiniplayerPlayIv.setOnClickListener {
            setMiniPlayerStatus(false)
        }

        binding.mainMiniplayerPauseIv.setOnClickListener {
            setMiniPlayerStatus(true)
        }


        binding.mainBnv.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, HomeFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.lookFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, LookFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.searchFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, SearchFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.lockerFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, LockerFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

            }
            false
        }

    }

    private fun setMiniPlayerStatus(isPlaying: Boolean) {
        if (isPlaying) {
            binding.mainMiniplayerPlayIv.visibility = View.VISIBLE
            binding.mainMiniplayerPauseIv.visibility = View.GONE
        } else {
            binding.mainMiniplayerPlayIv.visibility = View.GONE
            binding.mainMiniplayerPauseIv.visibility = View.VISIBLE
        }
    }

    private fun initNavigation() {
        supportFragmentManager.beginTransaction().replace(R.id.main_frm, HomeFragment())
            .commitAllowingStateLoss()

    }

    private fun setMiniPlayer(song: Song){
        binding.mainMiniplayerTitleTv.text = song.title
        binding.mainMiniplayerSingerTv.text = song.singer
        binding.mainProgressSb.progress = (song.second*1000/song.playTime)
//      mediaPlayer = MediaPlayer.create(this,music)

        if(song.isPlaying){
            binding.mainMiniplayerPlayIv.visibility = View.GONE
            binding.mainMiniplayerPauseIv.visibility = View.VISIBLE
        }else{
            binding.mainMiniplayerPlayIv.visibility = View.VISIBLE
            binding.mainMiniplayerPauseIv.visibility = View.GONE
        }
    }

    override fun onStart() {
        super.onStart()

        val sqf = getSharedPreferences("song", MODE_PRIVATE)
        val songId = sqf.getInt("songId", 0)

        val songDB = SongDatabase. getInstance(this)!!
        song = if(songId == 0){
            songDB.SongDao().getSong(1)
        }else{
            songDB.SongDao().getSong(songId)
        }


        setMiniPlayer(song)
    }

    private fun inputDumpySongs(){
        val songDB = SongDatabase.getInstance(this)!!
        val songs = songDB.SongDao().getSongs()

        if(songs.isNotEmpty()) return

        songDB.SongDao().insert(
            Song(
                "Lilac",
                "아이유 (IU)",
                0,
                200,
                false,
                "music_lilac",
                R.drawable.img_album_exp2,
                false
            )
        )

        songDB.SongDao().insert(
            Song(
                "Butter",
                "방탄소년단 (BTS)",
                0,
                190,
                false,
                "music_lilac",
                R.drawable.img_album_exp,
                false
            )
        )

        songDB.SongDao().insert(
            Song(
                "Next Level",
                "에스파 (AESPA)",
                0,
                210,
                false,
                "music_lilac",
                R.drawable.img_album_exp3,
                false
            )
        )

        songDB.SongDao().insert(
            Song(
                "Boy with Luv",
                "방탄소년단 (BTS)",
                0,
                230,
                false,
                "music_lilac",
                R.drawable.img_album_exp4,
                false
            )
        )

        songDB.SongDao().insert(Song(
            "BBoom BBoom",
            "모모랜드 (MOMOLAND)",
            0,
            240,
            false,
            "music_lilac",
            R.drawable.img_album_exp5,
            false
        )
        )

        songDB.SongDao().insert(Song(
            "Weekend",
            "태연 (Tae Yeon)",
            0,
            210,
            false,
            "music_lilac",
            R.drawable.img_album_exp6,
            false
        )
        )

    }

}

