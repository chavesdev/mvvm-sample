package com.chavesdev.mvvmsamplecode.movies.core.di

import com.chavesdev.mvvmsamplecode.movies.core.data.repo.MovieRepositoryImpl
import com.chavesdev.mvvmsamplecode.movies.core.domain.repo.MoviesRepository
import com.chavesdev.mvvmsamplecode.movies.core.domain.usecases.GetMovieDetailsUseCase
import com.chavesdev.mvvmsamplecode.movies.core.domain.usecases.GetPopularMoviesUseCase
import com.chavesdev.mvvmsamplecode.movies.details.presentation.viewmodel.MovieDetailViewModel
import com.chavesdev.mvvmsamplecode.movies.popular.presentation.viewmodel.MoviesListViewModel
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::MovieRepositoryImpl) { bind<MoviesRepository>() }

    factoryOf(::GetPopularMoviesUseCase)
    factoryOf(::GetMovieDetailsUseCase)

    single<Picasso> { Picasso.get() }

    viewModel { MoviesListViewModel(get()) }
    viewModel { params ->
        MovieDetailViewModel(
            getMovieDetailsUseCase = get(),
            savedStateHandle = params.get()
        )
    }
}