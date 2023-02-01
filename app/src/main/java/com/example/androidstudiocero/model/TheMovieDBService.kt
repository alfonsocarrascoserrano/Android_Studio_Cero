package com.example.androidstudiocero.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDBService {

    @GET("movie/popular")
    suspend fun listPopularMovies(@Query("api_key")apiKey: String): MovieDBResult
}