package com.chavesdev.mvvmsamplecode.movies.core.di

import android.content.Context
import com.chavesdev.mvvmsamplecode.BuildConfig
import com.chavesdev.mvvmsamplecode.R
import com.chavesdev.mvvmsamplecode.movies.core.util.BearerInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(provideBearerInterceptor(get()))
            .addInterceptor(provideHttpLoggingInterceptor())
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder().baseUrl(
            androidContext().getString(
                R.string.base_url
            )
        )
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()

    }

}

fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    if (BuildConfig.DEBUG) {
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    } else {
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE)
    }
    return httpLoggingInterceptor
}

fun provideBearerInterceptor(context: Context): BearerInterceptor {
    return BearerInterceptor(context)
}