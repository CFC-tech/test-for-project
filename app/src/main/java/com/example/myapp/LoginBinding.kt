package com.example.myapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp.databinding.ActivityLoginBindingBinding

class LoginBinding : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBindingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBindingBinding.inflate(layoutInflater)
        val etName = intent.getStringExtra("Name")
        val etPassword = intent.getStringExtra("Password")
        binding.txtName.text = etName
        binding.txtPassword.text = etPassword
        setContentView(binding.root)
    }
}