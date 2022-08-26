package com.chavesdev.mvvmsamplecode.movies.core.util

import com.chavesdev.mvvmsamplecode.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class BearerInterceptor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var chainRequest = chain.request()
        val token = BuildConfig.TMDB_API_KEY
        val bearer = "Bearer $token"
        chainRequest = chainRequest.newBuilder().addHeader("Authorization", bearer).build()
        return chain.proceed(chainRequest)
    }
}