package com.chavesdev.mvvmsamplecode

import android.app.Application
import com.chavesdev.mvvmsamplecode.movies.core.di.apiModule
import com.chavesdev.mvvmsamplecode.movies.core.di.appModule
import com.chavesdev.mvvmsamplecode.movies.core.di.picassoModule
import com.chavesdev.mvvmsamplecode.movies.core.di.retrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                listOf(retrofitModule, apiModule, picassoModule, appModule)
            )
        }
    }
}