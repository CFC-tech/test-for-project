package com.example.myapp.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp.R
import com.example.myapp.databinding.ActivityLoginBinding

class CakeLogin : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        binding.btcreate.setOnClickListener {
            startActivity(
                android.content.Intent(
                    this,
                    CakeCreateAccount::class.java
                )
            )
        }


        setContentView(binding.root)
    }
}