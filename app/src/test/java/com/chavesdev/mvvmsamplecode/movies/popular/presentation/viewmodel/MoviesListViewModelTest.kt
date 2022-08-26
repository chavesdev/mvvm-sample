package com.chavesdev.mvvmsamplecode.movies.popular.presentation.viewmodel

import com.chavesdev.mvvmsamplecode.movies.core.domain.model.MoviesPage
import com.chavesdev.mvvmsamplecode.movies.core.domain.usecases.GetPopularMoviesUseCase
import com.chavesdev.mvvmsamplecode.movies.core.util.ResponseState
import com.chavesdev.mvvmsamplecode.movies.popular.presentation.MoviesListState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.Assert.assertFalse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class MoviesListViewModelTest {
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    private val getPopularMoviesUseCase: GetPopularMoviesUseCase = mockk(relaxed = true)
    private val responseStateSuccess: ResponseState.Success<MoviesPage> = mockk(relaxed = true)
    private val responseStatusError : ResponseState.Error<MoviesPage> = mockk(relaxed = true)
    private val moviesPage: MoviesPage = mockk(relaxed = true)

    private lateinit var moviesListViewModel: MoviesListViewModel

    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
        moviesListViewModel = MoviesListViewModel(getPopularMoviesUseCase, mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun `check moviesListState starts loading` () {
        setup()
        val moviesListState = MoviesListState(isLoading = true)
        val moviesListStateValue = moviesListViewModel.moviesListState.value
        assert(moviesListStateValue.isLoading == moviesListState.isLoading)
    }

    @Test
    fun `check viewmodel and usecase integration`() = runTest {
        coEvery { moviesPage.page } returns 1
        coEvery { moviesPage.totalPages } returns 1
        coEvery { moviesPage.results } returns emptyList()
        coEvery { responseStateSuccess.data } returns moviesPage
        coEvery { getPopularMoviesUseCase.invoke() } returns responseStateSuccess
        setup()
        coVerify { getPopularMoviesUseCase.invoke() }
        val moviesListStateValue = moviesListViewModel.moviesListState.value
        assertFalse(moviesListStateValue.isLoading)
        assert(moviesListStateValue.error.isEmpty())
    }

    @Test
    fun `check error message`() = runTest {
        coEvery { responseStatusError.message } returns null
        coEvery { getPopularMoviesUseCase.invoke() } returns responseStatusError
        setup()
        coVerify { getPopularMoviesUseCase.invoke() }
        val moviesListStateValue = moviesListViewModel.moviesListState.value
        assertFalse(moviesListStateValue.isLoading)
        assert(moviesListStateValue.error == "An unexpected error ocurred")
    }
}