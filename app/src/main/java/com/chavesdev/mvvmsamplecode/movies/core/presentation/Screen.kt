package com.chavesdev.mvvmsamplecode.movies.core.presentation

sealed class Screen(val route: String) {
    object MovieListScreen: Screen("movies_list_screen")
    object MovieDetailScreen: Screen("movie_detail_screen")
}
