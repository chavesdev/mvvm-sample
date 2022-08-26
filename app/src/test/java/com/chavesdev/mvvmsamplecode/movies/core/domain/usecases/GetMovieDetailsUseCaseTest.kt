package com.chavesdev.mvvmsamplecode.movies.core.domain.usecases

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.chavesdev.mvvmsamplecode.movies.core.domain.model.MovieDetail
import com.chavesdev.mvvmsamplecode.movies.core.domain.repo.MoviesRepository
import com.chavesdev.mvvmsamplecode.movies.core.util.ResponseState
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException
import java.io.IOException


@OptIn(ExperimentalCoroutinesApi::class)
internal class GetMovieDetailsUseCaseTest {

    @get:Rule
    val testCoroutineRule = InstantTaskExecutorRule()

    private val movieRepository: MoviesRepository = mockk(relaxed = true)
    private val httpException: HttpException = mockk(relaxed = true)
    private val ioException: IOException = mockk(relaxed = true)
    private val movieDetail: MovieDetail = mockk()

    private lateinit var getMovieDetailsUseCase: GetMovieDetailsUseCase

    @Before
    fun setup() {
        getMovieDetailsUseCase = GetMovieDetailsUseCase(movieRepository)
    }

    @Test
    fun `check success state`() = runBlockingTest {
        every { movieDetail.adult } returns true
        coEvery { movieRepository.getMovieDetails(any()) }.returns(movieDetail)
        val state = getMovieDetailsUseCase.invoke(1)
        assert(state is ResponseState.Success<MovieDetail>)
        val movieDetail = state.data
        assert(movieDetail?.adult ?: false)
    }

    @Test
    fun `check http exception`() = runBlockingTest {
        coEvery { movieRepository.getMovieDetails(any()) }.throws(httpException)
        val state = getMovieDetailsUseCase.invoke(1)
        assert(state is ResponseState.Error)
    }

    @Test
    fun `check io exception`() = runBlockingTest {
        coEvery { movieRepository.getMovieDetails(any()) }.throws(ioException)
        val state = getMovieDetailsUseCase.invoke(1)
        assert(state is ResponseState.Error)
    }

}