package com.example.myapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapp.databinding.ActivityMainBinding
import kotlin.jvm.java

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.btnLogin.setOnClickListener {
            Toast.makeText(this, "Login Button Clicked", Toast.LENGTH_SHORT).show()
            val etName = binding.etName.text.toString()
            val etPassword = binding.etPassword.text.toString()
            val intent = Intent( this, Login_Binding::class.java)
            intent.putExtra( "Name", etName)
            intent.putExtra( "Password", etPassword)

            startActivity(intent)
        }
        setContentView(binding.root)
    }
}