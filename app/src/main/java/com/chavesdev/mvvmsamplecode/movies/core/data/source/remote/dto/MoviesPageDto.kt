package com.chavesdev.mvvmsamplecode.movies.core.data.source.remote.dto

import com.chavesdev.mvvmsamplecode.movies.core.domain.model.MoviesPage
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MoviesPageDto(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<MovieDto>,
    @SerializedName("total_pages")
    val totalPages: Int
): Serializable

fun MoviesPageDto.toDomain(): MoviesPage {
    return MoviesPage(
        page = page,
        results = results.map { it.toDomain() },
        totalPages = totalPages
    )
}
