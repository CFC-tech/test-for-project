package com.example.myapp.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp.R
import com.example.myapp.databinding.ActivityCreateAccountBinding

class CakeCreateAccount : AppCompatActivity() {
    private lateinit var binding: ActivityCreateAccountBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}