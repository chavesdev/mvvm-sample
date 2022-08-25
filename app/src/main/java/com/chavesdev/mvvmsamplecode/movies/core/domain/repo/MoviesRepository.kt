package com.chavesdev.mvvmsamplecode.movies.core.domain.repo

import com.chavesdev.mvvmsamplecode.movies.core.domain.model.MovieDetail
import com.chavesdev.mvvmsamplecode.movies.core.domain.model.MoviesPage

interface MoviesRepository {
    suspend fun listPopularMovies(): MoviesPage
    suspend fun getMovieDetails(movieId: Int): MovieDetail
}