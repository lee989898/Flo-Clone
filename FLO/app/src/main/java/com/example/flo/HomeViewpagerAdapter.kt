package com.example.flo

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeViewpagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 ->  PanelFragment()
            1 -> PanelFragment()
            2 -> PanelFragment()
            3 -> PanelFragment()
            else -> PanelFragment()


        }
    }
}
