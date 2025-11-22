package com.example.myapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapp.R
import com.example.myapp.databinding.FragmentSeriesFGBinding
import com.example.myapp.databinding.FragmentSettingFGBinding

class SettingFG : Fragment() {

    private lateinit var binding: FragmentSettingFGBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingFGBinding.inflate(inflater, container, false)
        return binding.root
    }

}