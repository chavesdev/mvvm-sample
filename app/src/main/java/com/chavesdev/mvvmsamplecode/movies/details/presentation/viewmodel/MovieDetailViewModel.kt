package com.chavesdev.mvvmsamplecode.movies.details.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chavesdev.mvvmsamplecode.movies.core.domain.usecases.GetMovieDetailsUseCase
import com.chavesdev.mvvmsamplecode.movies.core.util.ResponseState
import com.chavesdev.mvvmsamplecode.movies.details.presentation.MovieDetailState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    val savedStateHandle: SavedStateHandle,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _movieDatailState = mutableStateOf(MovieDetailState())
    val movieDetailState: State<MovieDetailState> = _movieDatailState

    init {
        savedStateHandle.get<String>("movieId")?.let {
            getMovieDetails(it.toInt())
        }
    }

    private fun getMovieDetails(movieId: Int) {
        _movieDatailState.value = MovieDetailState(isLoading = true)
        viewModelScope.launch(dispatcher) {
            when (val response = getMovieDetailsUseCase(movieId)) {
                is ResponseState.Success -> {
                    _movieDatailState.value =
                        MovieDetailState(movie = response.data)
                }
                is ResponseState.Error -> {
                    _movieDatailState.value = MovieDetailState(
                        error = response.message ?: "An unexpected error ocurred"
                    )
                }

                else -> {}
            }
        }
    }
}