package com.example.flo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.flo.databinding.ActivitySongBinding

class SongActivity : AppCompatActivity() {


    lateinit var binding : ActivitySongBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent.hasExtra("title") && intent.hasExtra("singer")){
            binding.songMusicTitleTv.text = intent.getStringExtra("title")
            binding.songsingenrnametv.text = intent.getStringExtra("singer")
        }

        binding.songDownIb.setonClickListener{
            finish()
        }

        binding.songMiniplayerIv.setOnClcikListener{
            setPlayerStatus(false)
        }

        binding.songPauseIv.setOnClcikListener{
            setPlayerStatus(true)
        }



    }

    fun setPlayerStatus(isPlaying : Boolean){
        if(isPlaying){
            binding.songMiniPlayerIv.visibility = View.VISIBLE
            binding.songPauseIv.visibility = View.GONE
        }else{
            binding.songMiniPlayerIv.visibility = View.GONE
            binding.songPauseIv.visibility = View.VISIBLE
        }
    }


}