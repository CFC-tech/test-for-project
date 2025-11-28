package com.example.myapp.api

import com.example.myapp.model.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import java.sql.SQLInvalidAuthorizationSpecException

interface TmdbApi {

    @GET("movie/popular?language=en-US&page=1")
    suspend fun getPopularMovies(
        @Header("Authorization") authorization : String,
        @Header("accept") accept : String
    ): Response<Movie>

    @GET("movie/upcoming?language=en-US&page=1")
    suspend fun getUpcomingMovies(
        @Header("Authorization") authorization : String,
        @Header("accept") accept : String
    ): Response<Movie>

    @GET("top_rated?language=en-US&page=1")
    suspend fun getTopRatedMovies(
        @Header("Authorization") authorization: String,
        @Header("accept") accept: String
    ): Response<Movie>
}