package com.example.flo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.flo.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding

    val information = arrayListOf("","","","","")


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
//        binding.homeAlbumExp01Iv.clipToOutline = true
//        binding.homeAlbumExp02Iv.clipToOutline = true

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

        val panelAdapter = HomeViewpagerAdapter(this)
        binding.panelContentVp.adapter = panelAdapter
        TabLayoutMediator(binding.panelContentTb, binding.panelContentVp){
            tab, position ->
            tab.text = information[position]
        }.attach()

        return binding.root
    }


}