package com.example.flo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.flo.databinding.FragmentDetailBinding
import com.example.flo.databinding.FragmentSongBinding

class SongFragment : Fragment() {

    lateinit var binding : FragmentSongBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSongBinding.inflate(inflater, container, false)

        binding.songLalacLayout.setOnClickListener{
            Toast.makeText(activity, "라일락", Toast.LENGTH_SHORT).show()
        }

        binding.songFluLayout.setOnClickListener{
            Toast.makeText(activity, "Flu", Toast.LENGTH_SHORT).show()
        }

        binding.songCoinLayout.setOnClickListener{
            Toast.makeText(activity, "Coin", Toast.LENGTH_SHORT).show()
        }

        binding.songSpringLayout.setOnClickListener{
            Toast.makeText(activity, "봄 안녕", Toast.LENGTH_SHORT).show()
        }

        binding.songCelebrityLayout.setOnClickListener{
            Toast.makeText(activity, "Celebrity", Toast.LENGTH_SHORT).show()
        }

        binding.songSingLayout.setOnClickListener{
            Toast.makeText(activity, "돌림노래 (Feat. Dean)", Toast.LENGTH_SHORT).show()
        }




        binding.songMixoffTg.setOnClickListener {
            toggleStatus(true)
        }

        binding.songMixonTg.setOnClickListener {
            toggleStatus(false)
        }

        return binding.root
    }

    private fun toggleStatus(isSelected : Boolean){
        if(isSelected){
            binding.songMixoffTg.visibility = View.GONE
            binding.songMixonTg.visibility = View.VISIBLE
        }
        else{
            binding.songMixoffTg.visibility = View.VISIBLE
            binding.songMixonTg.visibility = View.GONE

        }

    }
}