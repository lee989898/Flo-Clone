package com.example.flo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.flo.databinding.FragmentPanel02Binding
import com.example.flo.databinding.FragmentPanel05Binding

class PanelFragment5 : Fragment(){

    lateinit var binding: FragmentPanel05Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPanel05Binding.inflate(inflater,container,false)



        return binding.root
    }

}