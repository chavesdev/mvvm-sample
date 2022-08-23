package com.chavesdev.mvvmsamplecode.movies.core.data.source.remote

import com.chavesdev.mvvmsamplecode.movies.core.data.source.remote.dto.MoviesPageDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesApi {

    @GET("movie/popular")
    suspend fun getPopular(): MoviesPageDto

    @GET("movie/{movie_id}")
    suspend fun getDetails(@Path("movie_id") movieId: Long): Response<MoviesPageDto>
}