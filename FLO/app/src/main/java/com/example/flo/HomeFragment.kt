package com.example.flo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.flo.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)


        binding.panelContentVp.adapter = ScreenSlidePagerAdapter(this)
        binding.panelContentVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        binding.homeSpringDotsIndicator.setViewPager2(binding.panelContentVp)


        binding.homeTodayAlbum01Iv.clipToOutline = true
        binding.homeTodayAlbum02Iv.clipToOutline = true
        binding.homeTodayAlbum03Iv.clipToOutline = true
        binding.homePotcast01Iv.clipToOutline = true
        binding.homePotcast02Iv.clipToOutline = true
        binding.homePotcast03Iv.clipToOutline = true
        binding.homeVideo01Iv.clipToOutline = true
        binding.homeVideo02Iv.clipToOutline = true
        binding.homeViewpagerExp2Iv.clipToOutline = true

        binding.homeTodayAlbum01Iv.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, AlbumFragment())
                .commitAllowingStateLoss()
        }

        val bannerAdapter = BannerViewpagerAdapter(this)
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp2))

        binding.homeBannerVp.adapter = bannerAdapter
        binding.homeBannerVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL



        return binding.root
    }

    private inner class ScreenSlidePagerAdapter(fragment: HomeFragment) : FragmentStateAdapter(fragment){

        override fun getItemCount(): Int = 5

        override fun createFragment(position: Int): Fragment {
            return when(position){
                0 -> PanelFragment()
                1 -> PanelFragment()
                2 -> PanelFragment()
                3 -> PanelFragment()
                else -> PanelFragment()
            }
        }
    }


}