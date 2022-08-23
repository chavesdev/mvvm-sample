package com.chavesdev.mvvmsamplecode.movies.core.domain.usecases

import com.chavesdev.mvvmsamplecode.movies.core.domain.repo.MoviesRepository
import com.chavesdev.mvvmsamplecode.movies.core.util.ResponseState
import retrofit2.HttpException
import java.io.IOException

class GetPopularMoviesUseCase(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke(): ResponseState {
        return try {
            val response = moviesRepository.listPopularMovies()
            ResponseState.Success(response)
        } catch (http: HttpException) {
            ResponseState.Error(http)
        } catch (io: IOException) {
            ResponseState.Error(io)
        }
    }
}