package com.chavesdev.mvvmsamplecode.movies.core.di

import com.chavesdev.mvvmsamplecode.movies.core.data.source.remote.MoviesApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {

    single<MoviesApi> { get<Retrofit>().create(MoviesApi::class.java) }

}