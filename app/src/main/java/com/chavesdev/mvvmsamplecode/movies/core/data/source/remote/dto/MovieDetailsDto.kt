package com.chavesdev.mvvmsamplecode.movies.core.data.source.remote.dto

import com.chavesdev.mvvmsamplecode.movies.core.domain.model.Genre
import com.chavesdev.mvvmsamplecode.movies.core.domain.model.MovieDetail
import com.google.gson.annotations.SerializedName

data class MovieDetailsDto(
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
    val voteAverage: Float,
    @SerializedName("genres")
    val genres: List<GenreDto>
)

data class GenreDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)

fun GenreDto.toDomain() : Genre {
    return Genre(
        id = id,
        name = name
    )
}

fun MovieDetailsDto.toDomain(): MovieDetail {
    return MovieDetail(
        id = id,
        adult = adult,
        backdropPath = backdropPath,
        posterPath = posterPath,
        language = originalLanguage,
        title = title,
        overview = overview,
        popularity = popularity,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        genres = genres.map { it.toDomain() }
    )
}
