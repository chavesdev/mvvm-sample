package com.chavesdev.mvvmsamplecode.movies.popular.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.chavesdev.mvvmsamplecode.movies.core.presentation.Screen
import com.chavesdev.mvvmsamplecode.movies.popular.presentation.ui.components.MovieListItem
import com.chavesdev.mvvmsamplecode.movies.popular.presentation.viewmodel.MoviesListViewModel

@Composable
fun PopularMovieListScreen(
    navController: NavController,
    moviesListViewModel: MoviesListViewModel
) {
    val state = moviesListViewModel.moviesListState.value
    val lazyGridState = rememberLazyGridState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize(),
            state = lazyGridState,
        ) {
            item(span = {GridItemSpan(3)}) {
                Text(
                    text = "Popular Movies",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(20.dp))
            }

            items(items = state.movies) { movie ->
                MovieListItem(
                    movie = movie,
                    onItemClick = {
                        navController.navigate(Screen.MovieDetailScreen.route + "/${movie.id}")
                    }
                )
            }
        }

        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .align(Alignment.Center)
            )
        }

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

}