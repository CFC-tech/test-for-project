package com.example.myapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp.R
import com.example.myapp.adapter.MovieAdapter
import com.example.myapp.databinding.FragmentHomeFGBinding
import com.example.myapp.model.Movie

class HomeFG : Fragment() {



    private lateinit var binding: FragmentHomeFGBinding

    private var movielist : ArrayList<Movie> = arrayListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeFGBinding.inflate(inflater, container, false)
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.popularRV.layoutManager = layoutManager
        val layoutManager2 = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.UpcomingRV.layoutManager = layoutManager2
        val layoutManager3 = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.TopRatedRV.layoutManager = layoutManager3

        var movie1 = Movie("Edge", R.drawable.edge)
        var movie2 = Movie("Family Plan", R.drawable.family_plan)
        var movie3 = Movie("Play Date", R.drawable.playdate)
        var movie4 = Movie("12 Angry Men", R.drawable.angry_men)
        var movie5 = Movie("Pulp Fiction", R.drawable.pulp_fiction)
        var movie6 = Movie("Spirited Away", R.drawable.spirited_away)
        movielist.add(movie1)
        movielist.add(movie2)
        movielist.add(movie3)
        movielist.add(movie4)
        movielist.add(movie5)
        movielist.add(movie6)

        val movie_adapter = MovieAdapter(movielist, requireContext())
        binding.popularRV.adapter = movie_adapter
        binding.UpcomingRV.adapter = movie_adapter
        binding.TopRatedRV.adapter = movie_adapter

        return binding.root

    }


}