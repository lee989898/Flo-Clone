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
import com.google.gson.Gson


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
            add(Album("All I Wanna Do", "박재범", R.drawable.park))
            add(Album("Lilac", "아이유 (IU)", R.drawable.img_album_exp2))
            add(Album("첫눈처럼 너에게 가겠다", "에일리", R.drawable.aille))
            add(Album("네 시", "방탄소년단 (BTS)", R.drawable.bts))
            add(Album("Closer", "The Chainsmokers", R.drawable.chain))
            add(Album("Why Don't You Know", "청하", R.drawable.cheonha))
            add(Album("D (Half Moon)", "Dean", R.drawable.dean))
            add(Album("오래전 그날", "DNCE, WAKAWAKA", R.drawable.dnce))
            add(Album("Warm on a Cold Night", "HONNE", R.drawable.honne))
            add(Album("Butter", "방탄소년단 (BTS)", R.drawable.img_album_exp))
            add(Album("18거 1517", "XXX (BTS)", R.drawable.language))
        }


        // 더미데이터랑 Adapter 연결
        val albumRVAdapter = AlbumRVAdapter(albumDates)
        // 리사이클러뷰에 어댑터를 연결
        binding.homeTodayMusicAlbumRecycleerView.adapter = albumRVAdapter

        albumRVAdapter.setMyItemClickListener(object : AlbumRVAdapter.MyItemClickListener{

            override fun onItemClick(album: Album) {
                changeAlbumFragment(album)
            }

//            override fun onRemveAlbum(position: Int) {
//                albumRVAdapter.removeItem(position)
//            }
        })

        // 레이아웃 매니저 설정
        binding.homeTodayMusicAlbumRecycleerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)

        val bannerAdapter = BannerViewpagerAdapter(this)
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp2))

        binding.homeBannerVp.adapter = bannerAdapter
        binding.homeBannerVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL



        return binding.root
    }

    private fun changeAlbumFragment(album: Album) {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, AlbumFragment().apply {
                arguments = Bundle().apply {
                    val gson = Gson()
                    val albumJson = gson.toJson(album)
                    putString("album", albumJson)
                }
            })
            .commitAllowingStateLoss()
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