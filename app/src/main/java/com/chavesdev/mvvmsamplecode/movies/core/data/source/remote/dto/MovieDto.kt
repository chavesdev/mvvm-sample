package com.chavesdev.mvvmsamplecode.movies.core.data.source.remote.dto

import com.chavesdev.mvvmsamplecode.movies.core.domain.model.Movie
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: Float,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("vote_average")
    val voteAverage: Float
) : Serializable

fun MovieDto.toDomain() : Movie {
    return Movie(
        id = id,
        adult = adult,
        backdropPath = backdropPath,
        posterPath = posterPath,
        language = originalLanguage,
        title = title,
        overview = overview,
        popularity = popularity,
        releaseDate = releaseDate,
        voteAverage = voteAverage
    )
}
