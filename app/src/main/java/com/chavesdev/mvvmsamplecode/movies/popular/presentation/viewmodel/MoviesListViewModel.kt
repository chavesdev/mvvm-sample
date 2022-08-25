package com.chavesdev.mvvmsamplecode.movies.popular.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chavesdev.mvvmsamplecode.movies.core.domain.usecases.GetPopularMoviesUseCase
import com.chavesdev.mvvmsamplecode.movies.core.util.ResponseState
import com.chavesdev.mvvmsamplecode.movies.popular.presentation.MoviesListState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesListViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) :
    ViewModel() {

    private val _moviesListState = mutableStateOf(MoviesListState())
    val moviesListState: State<MoviesListState> = _moviesListState

    init {
        getPopularMovies()
    }

    private fun getPopularMovies() {
        _moviesListState.value = MoviesListState(isLoading = true)
        viewModelScope.launch(dispatcher) {
            when (val response = getPopularMoviesUseCase()) {
                is ResponseState.Success -> {
                    val moviesPage = response.data
                    _moviesListState.value =
                        MoviesListState(movies = moviesPage?.results ?: emptyList())
                }
                is ResponseState.Error -> {
                    _moviesListState.value = MoviesListState(
                        error = response.message ?: "An unexpected error ocurred"
                    )
                }

                else -> {}
            }
        }
    }
}