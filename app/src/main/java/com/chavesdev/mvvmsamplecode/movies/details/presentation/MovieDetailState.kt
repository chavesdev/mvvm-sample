package com.chavesdev.mvvmsamplecode.movies.details.presentation

import com.chavesdev.mvvmsamplecode.movies.core.domain.model.MovieDetail

data class MovieDetailState(
    val isLoading: Boolean = false,
    val movie: MovieDetail? = null,
    val error: String = ""
)
