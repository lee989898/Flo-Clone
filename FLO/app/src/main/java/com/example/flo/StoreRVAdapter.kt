package com.example.flo

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ExpandableListView
import androidx.recyclerview.widget.RecyclerView
import com.example.flo.databinding.ItemStoreBinding

class StoreRVAdapter(private val storeList: ArrayList<Store>) : RecyclerView.Adapter<StoreRVAdapter.ViewHolder>(){

    // 클릭 인터페이스 정의
    interface MyStoreClickListener{
        fun onRemoveStore(position: Int)
    }

    // 리스너 객체를 전달받는 함수랑 리스너 객체를 저장할 변수
    private lateinit var mStoreClickListener: MyStoreClickListener

    fun setMyStoreClickListener(storeClickListener: MyStoreClickListener){
        mStoreClickListener = storeClickListener
    }


    // 뷰홀더를 생성해줘야 할 때 호출되는 함수 => 아이템 뷰 객체를 만들어서 뷰홀더에 던져줌
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): StoreRVAdapter.ViewHolder {
        val binding: ItemStoreBinding = ItemStoreBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)

        return ViewHolder(binding)

    }

    fun removeStore(position: Int){
        storeList.removeAt(position)
        notifyDataSetChanged()
    }

    // 뷰홀더에 데이터를 바인딩 해줘야 할 때마다 호출되는 함수 => 엄청나게 많이 호출
    override fun onBindViewHolder(holder: StoreRVAdapter.ViewHolder, position: Int) {
        holder.bind(storeList[position])
        holder.binding.storeOptionIv.setOnClickListener {  mStoreClickListener.onRemoveStore(position)}
    }

    // 데이터 세트 크기를 알려주는 함수 => 리사이클러뷰가 마지막이 언제인지를 알게 된다
    override fun getItemCount(): Int = storeList.size

    // 뷰홀더
    inner class ViewHolder(val binding: ItemStoreBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(store: Store){
            binding.storeAlbumTitleTv.text = store.title
            binding.storeAlbumSignerTv.text = store.singer
            binding.storeAlbumCoverImgIv.setImageResource(store.coverImg!!)
        }
    }

}