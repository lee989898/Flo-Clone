package com.example.flo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.flo.databinding.FragmentAlbumBinding

class AlbumFragment : Fragment() {

    lateinit var binding: FragmentAlbumBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAlbumBinding.inflate(inflater, container, false)


        binding.albumAlbumImgIv.clipToOutline = true

        binding.albumArrowBlackIb.setOnClickListener{
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, HomeFragment())
                .commitAllowingStateLoss()
        }

        binding.albumLailacLayout.setOnClickListener{
            Toast.makeText(activity, "라일락", Toast.LENGTH_SHORT).show()
        }

        binding.albumFluLayout.setOnClickListener{
            Toast.makeText(activity, "Flu", Toast.LENGTH_SHORT).show()
        }

        binding.albumCoinLayout.setOnClickListener{
            Toast.makeText(activity, "Coin", Toast.LENGTH_SHORT).show()
        }

        binding.albumSpringHelloLayout.setOnClickListener{
            Toast.makeText(activity, "봄 안녕", Toast.LENGTH_SHORT).show()
        }

        binding.albumCelebrityLayout.setOnClickListener{
            Toast.makeText(activity, "Celebrity", Toast.LENGTH_SHORT).show()
        }

        binding.albumRecycleLayout.setOnClickListener{
            Toast.makeText(activity, "돌림노래 (Feat. Dean)", Toast.LENGTH_SHORT).show()
        }

        binding.albumEmptycupLayout.setOnClickListener{
            Toast.makeText(activity, "빈 컵 (Empty Cup)", Toast.LENGTH_SHORT).show()
        }

        binding.albumKidandoceanLayout.setOnClickListener{
            Toast.makeText(activity, "아이와 나의 바다", Toast.LENGTH_SHORT).show()
        }

        binding.albumAhpuhLayout.setOnClickListener{
            Toast.makeText(activity, "어푸 (Ah puh)", Toast.LENGTH_SHORT).show()
        }


        binding.albumToggleOffIv.setOnClickListener {
            toggleStatus(true)
        }

        binding.albumToggleOnIv.setOnClickListener {
            toggleStatus(false)
        }



        return binding.root
    }

    private fun toggleStatus(isSelected : Boolean){
        if(isSelected){
            binding.albumToggleOffIv.visibility = View.GONE
            binding.albumToggleOnIv.visibility = View.VISIBLE
        }
        else{
            binding.albumToggleOffIv.visibility = View.VISIBLE
            binding.albumToggleOnIv.visibility = View.GONE

        }

    }


}