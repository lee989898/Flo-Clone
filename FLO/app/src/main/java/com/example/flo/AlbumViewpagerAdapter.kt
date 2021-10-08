package com.example.flo

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class AlbumViewpagerAdapter(fragmet: Fragment) : FragmentStateAdapter(fragmet) {


    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        TODO()
    }


}