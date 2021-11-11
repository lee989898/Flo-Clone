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
            add(Store("All I Wanna Do", "박재범", R.drawable.park))
            add(Store("Lilac", "아이유 (IU)", R.drawable.img_album_exp2))
            add(Store("첫눈처럼 너에게 가겠다", "에일리", R.drawable.aille))
            add(Store("네 시", "방탄소년단 (BTS)", R.drawable.bts))
            add(Store("Closer", "The Chainsmokers", R.drawable.chain))
            add(Store("Why Don't You Know", "청하", R.drawable.cheonha))
            add(Store("D (Half Moon)", "Dean", R.drawable.dean))
            add(Store("오래전 그날", "DNCE, WAKAWAKA", R.drawable.dnce))
            add(Store("Warm on a Cold Night", "HONNE", R.drawable.honne))
            add(Store("Butter", "방탄소년단 (BTS)", R.drawable.img_album_exp))
            add(Store("18거 1517", "XXX", R.drawable.language))
        }



        // 더미데이터랑 Adapter 연결
        val storeRVAdapter = StoreRVAdapter(storeDatas)
        // 리사이클러 뷰에 어댑터를 연결
        binding.storeRecyclyerView.adapter = storeRVAdapter

        storeRVAdapter.setMyStoreClickListener(object : StoreRVAdapter.MyStoreClickListener{

            override fun onRemoveStore(position: Int){
                storeRVAdapter.removeStore(position)
            }
        })

        // 레이아웃 매니저 설정
        binding.storeRecyclyerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        return binding.root
    }


}