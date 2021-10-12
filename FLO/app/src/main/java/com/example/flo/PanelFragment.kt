package com.example.flo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.flo.databinding.FragmentPanel01Binding

class PanelFragment : Fragment(){

    lateinit var binding: FragmentPanel01Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPanel01Binding.inflate(inflater,container,false)



        return binding.root
    }

}