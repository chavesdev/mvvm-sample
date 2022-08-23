package com.chavesdev.mvvmsamplecode.movies.core.domain.model

data class Movie(
    val id: Long,
    val adult: Boolean,
    val backdropPath: String,
    val posterPath: String,
    val language: String,
    val title: String,
    val overview: String,
    val popularity: Float,
    val releaseDate: String,
    val voteAverage: Float
)
