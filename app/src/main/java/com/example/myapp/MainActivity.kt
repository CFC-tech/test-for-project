package com.example.myapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ReportFragment
import com.example.myapp.databinding.ActivityMainBinding
import com.example.myapp.view.fragment.HomeFG
import com.example.myapp.view.fragment.MovieFG
import com.example.myapp.view.fragment.SeriesFG
import com.example.myapp.view.fragment.SettingFG
import java.io.Serial
import kotlin.jvm.java

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(fragment = HomeFG())

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.home -> {
                    loadFragment(fragment = HomeFG())
                }
                R.id.series -> {
                    loadFragment(fragment = SeriesFG())
                }
                R.id.movie -> {
                    loadFragment(fragment = MovieFG())
                }
                R.id.setting -> {
                    loadFragment(fragment = SettingFG())
                }
            }
            true
        }

    }

    fun loadFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.Flayout,fragment)
        fragmentTransaction.commit()
    }

}