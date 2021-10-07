package com.example.flo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.flo.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.homeTodayAlbum01Iv.clipToOutline = true
        binding.homeTodayAlbum02Iv.clipToOutline = true
        binding.homeTodayAlbum03Iv.clipToOutline = true
        binding.homePotcast01Iv.clipToOutline = true
        binding.homePotcast02Iv.clipToOutline = true
        binding.homePotcast03Iv.clipToOutline = true
        binding.homeVideo01Iv.clipToOutline = true
        binding.homeVideo02Iv.clipToOutline = true
        binding.homeViewpagerExp2Iv.clipToOutline = true

        binding.homeTodayAlbum01Iv.setOnClickListener{
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, AlbumFragment())
                .commitAllowingStateLoss()
        }

        return binding.root
    }


}