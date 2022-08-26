package com.chavesdev.mvvmsamplecode.movies.core.data.repo

import com.chavesdev.mvvmsamplecode.movies.core.data.source.remote.MoviesApi
import com.chavesdev.mvvmsamplecode.movies.core.data.source.remote.dto.MovieDetailsDto
import com.chavesdev.mvvmsamplecode.movies.core.data.source.remote.dto.MoviesPageDto
import com.chavesdev.mvvmsamplecode.movies.core.data.source.remote.dto.toDomain
import com.chavesdev.mvvmsamplecode.movies.core.domain.repo.MoviesRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
internal class MovieRepositoryImplTest {

    private val moviesApi: MoviesApi = mockk()
    private val moviesPageDto: MoviesPageDto = mockk(relaxed = true)
    private val movieDetailsDto: MovieDetailsDto = mockk(relaxed = true)
    private lateinit var moviesRepository: MoviesRepository

    @Before
    fun setup() {
        moviesRepository = MovieRepositoryImpl(moviesApi)
    }

    @Test
    fun `verify and assert listPopularMovies flow`() = runBlockingTest {
        coEvery { moviesApi.getPopular() } returns moviesPageDto
        val moviesPage = moviesRepository.listPopularMovies()
        coVerify { moviesPageDto.toDomain() }
        assert(moviesPage.page == moviesPageDto.page)
    }

    @Test
    fun `verify and assert getMovieDetails flow`() = runBlockingTest {
        coEvery { moviesApi.getDetails(any()) } returns movieDetailsDto
        val movieDetails = moviesRepository.getMovieDetails(1)
        coVerify { movieDetailsDto.toDomain() }
        assert(movieDetails.id == movieDetailsDto.id)
    }
}