package com.example.flo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flo.databinding.FragmentStoreBinding
import com.google.gson.Gson

class StoreFragment: Fragment() {

    lateinit var binding : FragmentStoreBinding
    private var gson: Gson = Gson()
    private var storeDatas = ArrayList<Store>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoreBinding.inflate(inflater, container, false)

        // 데이터 리스트 생성 더미 데이터
        storeDatas.apply {
            add(Store("Butter", "방탄소년단(BTS)", R.drawable.img_album_exp2))
            add(Store("Lilac", "아이유 (IU)", R.drawable.img_album_exp2))
            add(Store("Next Level", "에스파 (AESPA)", R.drawable.img_album_exp2))
            add(Store("Boy with Luv", "방탄소년단(BTS)", R.drawable.img_album_exp2))
            add(Store("BBoom BBoom", "모모랜드 (MOMOLAND)", R.drawable.img_album_exp2))
            add(Store("Weekend", "태연 (Tae Yeon)", R.drawable.img_album_exp2))
        }

        // 더미데이터랑 Adapter 연결
        val storeRVAdapter = StoreRVAdapter(storeDatas)
        // 리사이클러 뷰에 어댑터를 연결
        binding.storeRecyclyerView.adapter = storeRVAdapter
        // 레이아웃 매니저 설정
        binding.storeRecyclyerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        return binding.root
    }


}