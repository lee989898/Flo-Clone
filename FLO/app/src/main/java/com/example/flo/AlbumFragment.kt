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

        binding.albumArrowBlackIb.setOnClickListener{
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, HomeFragment())
                .commitAllowingStateLoss()
        }

        binding.albumLailacLayout.setOnClickListener{
            Toast.makeText(activity, "라일락", Toast.LENGTH_SHORT).show()
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