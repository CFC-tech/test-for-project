package com.example.myapp.view.fragment

import android.content.Context.CONNECTIVITY_SERVICE
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

    private val apiKey =
        "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlMjhjYmE4YjYxYTJlMTY0MmJkYjBkNjYwNTUxZjRhMSIsIm5iZiI6MTc2Mjk1MTM2Ny43MTUwMDAyLCJzdWIiOiI2OTE0ODBjNzNjZDgwY2UxN2YzYTNjNTkiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.S8iFq6XCYccqNPfHbSf_ItrBq31A5xfwxYOfqxBCuIY"

    private lateinit var binding: FragmentHomeFGBinding
    private lateinit var repository: MovieRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repository = MovieRepository()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeFGBinding.inflate(inflater, container, false)

        // LayoutManagers
        binding.popularRV.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.UpcomingRV.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.TopRatedRV.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        // >>> Show loading before starting API calls
        binding.loadingProgressBar.visibility = View.VISIBLE

        lifecycleScope.launch {
            try {
                // Option 1: Sequential calls
                val popularMovielist = repository.fetchPopularMovies(apiKey)
                val upcomingMovieList = repository.fetchUpcomingMovies(apiKey)
                val topRatedMovieList = repository.fetchTopRatedMovies(apiKey)

                // Adapter Set
                binding.popularRV.adapter = MovieAdapter(popularMovielist ?: emptyList(), requireContext())
                binding.UpcomingRV.adapter = MovieAdapter(upcomingMovieList ?: emptyList(), requireContext())
                binding.TopRatedRV.adapter = MovieAdapter(topRatedMovieList ?: emptyList(), requireContext())

            } catch (e: Exception) {
                Log.e("HomeFG", "API error: ${e.message}", e)
                // Optional: Toast message
                Toast.makeText(requireContext(), "Network error. Please try again.", Toast.LENGTH_SHORT).show()
            } finally {
                // >>> Hide loading after all done (success or error)
                binding.loadingProgressBar.visibility = View.GONE
            }
        }

        return binding.root
    }
}
