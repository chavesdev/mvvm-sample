package com.chavesdev.mvvmsamplecode.movies.core.di

import com.chavesdev.mvvmsamplecode.movies.core.data.repo.MovieRepositoryImpl
import com.chavesdev.mvvmsamplecode.movies.core.domain.repo.MoviesRepository
import com.chavesdev.mvvmsamplecode.movies.core.domain.usecases.GetPopularMoviesUseCase
import com.chavesdev.mvvmsamplecode.movies.popular.presentation.viewmodel.MoviesListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::MovieRepositoryImpl) { bind<MoviesRepository>() }

    factoryOf(::GetPopularMoviesUseCase)

    viewModel { MoviesListViewModel(get()) }
}