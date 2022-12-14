package com.chavesdev.mvvmsamplecode.movies.popular.presentation.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.chavesdev.mvvmsamplecode.R
import com.chavesdev.mvvmsamplecode.movies.core.presentation.components.MovieImage

@Composable
fun MoviePoster(posterPath: String, contentDescription: String, modifier: Modifier) {

    val url = (LocalContext.current).getString(R.string.base_poster_img_url) + posterPath

    MovieImage(url = url, contentDescription = contentDescription, modifier = modifier)
}