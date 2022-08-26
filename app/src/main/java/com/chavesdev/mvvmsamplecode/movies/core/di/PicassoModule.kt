package com.chavesdev.mvvmsamplecode.movies.core.di

import com.squareup.picasso.Picasso
import org.koin.dsl.module

val picassoModule = module {
    single<Picasso> { Picasso.get() }
}