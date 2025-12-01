package com.example.myapp.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp.R
import com.example.myapp.adapter.MovieAdapter
import com.example.myapp.databinding.FragmentHomeFGBinding
import com.example.myapp.model.Movie
import com.example.myapp.repository.MovieRepository
import kotlinx.coroutines.launch

class HomeFG : Fragment() {

    private val apiKey = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlMjhjYmE4YjYxYTJlMTY0MmJkYjBkNjYwNTUxZjRhMSIsIm5iZiI6MTc2Mjk1MTM2Ny43MTUwMDAyLCJzdWIiOiI2OTE0ODBjNzNjZDgwY2UxN2YzYTNjNTkiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.S8iFq6XCYccqNPfHbSf_ItrBq31A5xfwxYOfqxBCuIY"


    private lateinit var binding: FragmentHomeFGBinding
    private var movielist : ArrayList<Movie> = arrayListOf()

    private lateinit var repository: MovieRepository

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        repository = MovieRepository()
    }




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


        lifecycleScope.launch {
            val popularMovielist = repository.fetchPopularMovies(apiKey)
            val upcomingMovieList = repository.fetchUpcomingMovies(apiKey)
            val topRatedMovieList = repository.fetchTopRatedMovies(apiKey)

            val popularAdapter = MovieAdapter(popularMovielist?: emptyList(), requireContext())
            binding.popularRV.adapter = popularAdapter

            val upcomingAdapter = MovieAdapter(upcomingMovieList?: emptyList(), requireContext())
            binding.UpcomingRV.adapter = upcomingAdapter

            val topRatedAdapter = MovieAdapter(topRatedMovieList?: emptyList(), requireContext())
            binding.TopRatedRV.adapter = topRatedAdapter
        }

        return binding.root

    }


}