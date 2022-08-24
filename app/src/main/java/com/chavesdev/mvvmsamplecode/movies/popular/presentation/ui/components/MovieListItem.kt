package com.chavesdev.mvvmsamplecode.movies.popular.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.chavesdev.mvvmsamplecode.movies.core.domain.model.Movie

@Composable
fun MovieListItem(movie: Movie, onItemClick: (Movie) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().clickable { onItemClick(movie) }.padding(20.dp)
    ) {
        Text(
            text = movie.title,
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Ellipsis
        )
    }
}