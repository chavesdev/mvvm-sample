package com.chavesdev.mvvmsamplecode.movies.core.domain.usecases

import com.chavesdev.mvvmsamplecode.movies.core.domain.model.MoviesPage
import com.chavesdev.mvvmsamplecode.movies.core.domain.repo.MoviesRepository
import com.chavesdev.mvvmsamplecode.movies.core.util.ResponseState
import retrofit2.HttpException
import java.io.IOException

class GetPopularMoviesUseCase(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke(): ResponseState<MoviesPage> {
        return try {
            val response = moviesRepository.listPopularMovies()
            ResponseState.Success(response)
        } catch (http: HttpException) {
            ResponseState.Error(http.localizedMessage ?: "An unexcpeted error ocurred")
        } catch (io: IOException) {
            ResponseState.Error(io.localizedMessage ?: "Couldn't reach server. Check your internet connection")
        }
    }
}