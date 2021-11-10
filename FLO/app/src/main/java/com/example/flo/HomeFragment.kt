package com.example.flo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.flo.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    private var albumDates = ArrayList<Album>();



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)


        binding.panelContentVp.adapter = ScreenSlidePagerAdapter(this)
        binding.panelContentVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        binding.homeSpringDotsIndicator.setViewPager2(binding.panelContentVp)


        binding.homePotcast01Iv.clipToOutline = true
        binding.homePotcast02Iv.clipToOutline = true
        binding.homePotcast03Iv.clipToOutline = true
        binding.homeVideo01Iv.clipToOutline = true
        binding.homeVideo02Iv.clipToOutline = true
        binding.homeViewpagerExp2Iv.clipToOutline = true

//        binding.homeTodayAlbum01Iv.setOnClickListener {
//            (context as MainActivity).supportFragmentManager.beginTransaction()
//                .replace(R.id.main_frm, AlbumFragment())
//                .commitAllowingStateLoss()
//        }

        // 데이터 리스트 생성 더미 데이터
        albumDates.apply {
            add(Album("Butter", "방탄소년단(BTS)", R.drawable.img_album_exp2))
            add(Album("Lilac", "아이유 (IU)", R.drawable.img_album_exp2))
            add(Album("Next Level", "에스파 (AESPA)", R.drawable.img_album_exp2))
            add(Album("Boy with Luv", "방탄소년단(BTS)", R.drawable.img_album_exp2))
            add(Album("BBoom BBoom", "모모랜드 (MOMOLAND)", R.drawable.img_album_exp2))
            add(Album("Weekend", "태연 (Tae Yeon)", R.drawable.img_album_exp2))
        }

        // 더미데이터랑 Adapter 연결
        val albumRVAdapter = AlbumRVAdapter(albumDates)
        // 리사이클러뷰에 어댑터를 연결
        binding.homeTodayMusicAlbumRecycleerView.adapter = albumRVAdapter
        // 레이아웃 매니저 설정
        binding.homeTodayMusicAlbumRecycleerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)

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
                1 -> PanelFragment2()
                2 -> PanelFragment3()
                3 -> PanelFragment4()
                else -> PanelFragment5()
            }
        }
    }


}