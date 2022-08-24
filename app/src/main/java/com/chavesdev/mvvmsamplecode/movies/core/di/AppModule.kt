package com.chavesdev.mvvmsamplecode.movies.core.di

import com.chavesdev.mvvmsamplecode.movies.core.data.repo.MovieRepositoryImpl
import com.chavesdev.mvvmsamplecode.movies.core.domain.repo.MoviesRepository
import com.chavesdev.mvvmsamplecode.movies.core.domain.usecases.GetPopularMoviesUseCase
import org.koin.dsl.module

val appModule = module {
    single<MoviesRepository> { MovieRepositoryImpl(get()) }
    factory { GetPopularMoviesUseCase(get()) }
}