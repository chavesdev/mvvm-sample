package com.chavesdev.mvvmsamplecode.movies.details.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.StarRate
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.SavedStateHandle
import com.chavesdev.mvvmsamplecode.movies.core.domain.model.releaseYear
import com.chavesdev.mvvmsamplecode.movies.details.presentation.ui.components.MovieBackdropImage
import com.chavesdev.mvvmsamplecode.movies.details.presentation.viewmodel.MovieDetailViewModel
import com.chavesdev.mvvmsamplecode.movies.popular.presentation.ui.components.MoviePoster
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MovieDetailScreen(
    savedStateHandle: SavedStateHandle,
    movieDetailViewModel: MovieDetailViewModel = getViewModel(parameters = {
        parametersOf(
            savedStateHandle
        )
    })
) {
    val state = movieDetailViewModel.movieDetailState.value

    Box(modifier = Modifier.fillMaxSize()) {
        state.movie?.let { movie ->
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {

                    ConstraintLayout {
                        val (poster, title, middleRow, titleOverview, overview) = createRefs()

                        MovieBackdropImage(
                            posterPath = movie.backdropPath,
                            contentDescription = movie.title,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(260.dp)
                        )

                        MoviePoster(
                            posterPath = movie.posterPath,
                            contentDescription = movie.title,
                            modifier = Modifier
                                .height(120.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .constrainAs(poster) {
                                    top.linkTo(parent.top, margin = 180.dp)
                                    start.linkTo(parent.start, margin = 20.dp)
                                }
                        )

                        Text(
                            text = movie.title,
                            style = MaterialTheme.typography.h6,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .constrainAs(title) {
                                    start.linkTo(poster.end, margin = 15.dp)
                                    top.linkTo(poster.top, margin = 70.dp)
                                    end.linkTo(parent.end, margin= 20.dp)
                                    width = Dimension.fillToConstraints
                                }
                                .padding(end = 20.dp)
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.constrainAs(middleRow) {
                                top.linkTo(poster.bottom, margin = 30.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                        ) {
                            Column {
                                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                    Icon(
                                        Icons.Outlined.Language,
                                        contentDescription = "hate",
                                        modifier = Modifier.width(18.dp)
                                    )
                                    Text(text = movie.language.uppercase())
                                }
                            }
                            Column {
                                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                    Icon(
                                        Icons.Outlined.StarRate,
                                        contentDescription = "hate",
                                        modifier = Modifier.width(18.dp)
                                    )
                                    Text(text = String.format("%.2f", movie.voteAverage))
                                }

                            }
                            Column {
                                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                    Icon(
                                        Icons.Outlined.CalendarToday,
                                        contentDescription = "date",
                                        modifier = Modifier.width(18.dp)
                                    )
                                    Text(text = movie.releaseYear())
                                }
                            }
                        }

                        Text(
                            text = "Overview",
                            style = MaterialTheme.typography.subtitle1,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .constrainAs(titleOverview) {
                                    top.linkTo(middleRow.bottom, margin = 20.dp)
                                    width = Dimension.fillToConstraints
                                }
                                .padding(start = 20.dp, end = 20.dp)
                        )

                        Text(
                            text = movie.overview,
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier
                                .constrainAs(overview) {
                                    start.linkTo(parent.start, margin = 20.dp)
                                    top.linkTo(titleOverview.bottom, margin = 5.dp)
                                    end.linkTo(parent.end, margin = 20.dp)
                                }
                                .padding(start = 20.dp, end = 20.dp)
                        )
                    }

                }
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