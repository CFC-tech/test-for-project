package com.example.myapp.repository

import com.example.myapp.api.RetrofitClient
import com.example.myapp.model.Result


class MovieRepository {
    suspend fun fetchPopularMovies(apiKey: String) : List<Result>? {
        val response = RetrofitClient.api.getPopularMovies(apiKey, "application/json")
        return response.body()?.results
    }

    suspend fun fetchUpcomingMovies(apiKey : String): List<Result>? {
        val response = RetrofitClient.api.getUpcomingMovies(apiKey, "application/json")
        return response.body()?.results
    }

    suspend fun fetchTopRatedMovies(apiKey: String): List<Result>? {
        val response = RetrofitClient.api.getTopRatedMovies(apiKey, "application/json")
        return response.body()?.results
    }



}