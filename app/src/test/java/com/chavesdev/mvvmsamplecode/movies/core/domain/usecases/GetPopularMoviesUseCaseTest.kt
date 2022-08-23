package com.chavesdev.mvvmsamplecode.movies.core.domain.usecases

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.chavesdev.mvvmsamplecode.movies.core.domain.model.MoviesPage
import com.chavesdev.mvvmsamplecode.movies.core.domain.repo.MoviesRepository
import com.chavesdev.mvvmsamplecode.movies.core.util.ResponseState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException
import java.io.IOException


@OptIn(ExperimentalCoroutinesApi::class)
internal class GetPopularMoviesUseCaseTest {

    @get:Rule
    val testCoroutineRule = InstantTaskExecutorRule()

    private val movieRepository: MoviesRepository = mockk(relaxed = true)
    private val httpException: HttpException = mockk(relaxed = true)
    private val ioException: IOException = mockk(relaxed = true)

    private lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase

    @Before
    fun setUp() {
        getPopularMoviesUseCase = GetPopularMoviesUseCase(movieRepository)
    }

    @Test
    fun `check success state`() = runBlockingTest {
        coEvery { movieRepository.listPopularMovies() }.returns(successresponse)
        val state = getPopularMoviesUseCase.invoke()
        assert(state is ResponseState.Success)
        val moviesPage = state.data as MoviesPage
        assert(moviesPage.page == 1)
        assert(moviesPage.results.isEmpty())
        assert(moviesPage.totalPages == 1)
    }

    @Test
    fun `check http exception`() = runBlockingTest {
        coEvery { movieRepository.listPopularMovies() }.throws(httpException)
        val state = getPopularMoviesUseCase.invoke()
        assert(state is ResponseState.Error)
        assert(state.throwable is HttpException)
    }

    @Test
    fun `check io exception`() = runBlockingTest {
        coEvery { movieRepository.listPopularMovies() }.throws(ioException)
        val state = getPopularMoviesUseCase.invoke()
        assert(state is ResponseState.Error)
        assert(state.throwable is IOException)
    }

    companion object{
        val successresponse = MoviesPage(1, emptyList(), 1)
    }
}