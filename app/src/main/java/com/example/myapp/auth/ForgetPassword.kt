package com.example.myapp.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp.R
import com.example.myapp.databinding.ActivityForgetPasswordBinding

class ForgetPassword : AppCompatActivity() {
    private lateinit var binding: ActivityForgetPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}