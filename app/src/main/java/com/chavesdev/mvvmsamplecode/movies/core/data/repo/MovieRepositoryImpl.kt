package com.chavesdev.mvvmsamplecode.movies.core.data.repo

import com.chavesdev.mvvmsamplecode.movies.core.data.source.remote.MoviesApi
import com.chavesdev.mvvmsamplecode.movies.core.data.source.remote.dto.toDomain
import com.chavesdev.mvvmsamplecode.movies.core.domain.model.MoviesPage
import com.chavesdev.mvvmsamplecode.movies.core.domain.repo.MoviesRepository

class MovieRepositoryImpl(private val moviesApi: MoviesApi) : MoviesRepository {

    override suspend fun listPopularMovies(): MoviesPage {
        return moviesApi.getPopular().toDomain()
    }
}