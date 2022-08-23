package com.chavesdev.mvvmsamplecode.movies.core.domain.model

data class MoviesPage(
    val page: Int,
    val results: List<Movie>,
    val totalPages: Int
)