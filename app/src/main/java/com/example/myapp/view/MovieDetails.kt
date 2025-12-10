package com.example.myapp.view

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.myapp.R
import com.example.myapp.databinding.ActivityMovieDetailsBinding
import com.example.myapp.model.Result

class MovieDetails : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        enableEdgeToEdge()

        val movie = intent.extras?.getParcelable<Result>("movie_data")
        binding.txtTitle.text = movie?.title
        binding.txtOverview.text = movie?.overview


        val posterUrl = movie?.poster_path
        val posterImg = "https://image.tmdb.org/t/p/w500$posterUrl"


        Glide.with(this).load(posterImg).into(binding.imgPoster)




        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}