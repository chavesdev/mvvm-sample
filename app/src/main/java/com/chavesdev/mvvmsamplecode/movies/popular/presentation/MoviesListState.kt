package com.chavesdev.mvvmsamplecode.movies.popular.presentation

import com.chavesdev.mvvmsamplecode.movies.core.domain.model.Movie

data class MoviesListState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String = ""
)
