package com.example.flo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.flo.databinding.FragmentPanel02Binding
import com.example.flo.databinding.FragmentPanel04Binding

class PanelFragment4 : Fragment(){

    lateinit var binding: FragmentPanel04Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPanel04Binding.inflate(inflater,container,false)



        return binding.root
    }

}