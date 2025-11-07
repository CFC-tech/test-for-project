package com.example.myapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapp.databinding.ActivityLoginBinding
import com.example.myapp.databinding.ActivityLoginBindingBinding
import com.example.myapp.databinding.ActivityMainBinding

class Login_Binding : AppCompatActivity() {
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