package com.example.myapp.repository

import com.example.myapp.api.RetrofitClient


class MovieRepository {
    suspend fun fetchPopularMovies(apiKey: String){
        RetrofitClient.api.getPopularMovies(authorization = apiKey, accept = "application/json")
    }

    suspend fun fetchUpcomingMovies(apiKey : String){
        RetrofitClient.api.getUpcomingMovies(authorization = apiKey, accept = "application/json")
    }

    suspend fun fetchTopRatedMovies(apiKey: String){
        RetrofitClient.api.getTopRatedMovies(authorization = apiKey, accept = "application/json")
    }


}