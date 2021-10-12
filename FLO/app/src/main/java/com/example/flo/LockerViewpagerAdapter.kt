package com.example.flo

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.flo.databinding.FragmentSongFileBinding
import com.example.flo.databinding.FragmentStoreBinding

class LockerViewpagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> StoreFragment()
            else -> SongFileFragment()
        }
    }


}