package com.chavesdev.mvvmsamplecode.movies.core.domain.model

data class MovieDetail(
    val id: Long,
    val adult: Boolean,
    val backdropPath: String,
    val posterPath: String,
    val language: String,
    val title: String,
    val overview: String,
    val popularity: Float,
    val releaseDate: String,
    val voteAverage: Float,
    val genres: List<Genre>
)

fun MovieDetail.releaseYear(): String {
    return releaseDate.split("-").first()
}

data class Genre(val id: Int, val name: String)
