package com.chavesdev.mvvmsamplecode.movies.core.util

import android.content.Context
import com.chavesdev.mvvmsamplecode.R
import okhttp3.Interceptor
import okhttp3.Response

class BearerInterceptor(private val context: Context): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var chainRequest = chain.request()
        val token = context.getString(R.string.api_token)
        val bearer = "Bearer $token"
        chainRequest = chainRequest.newBuilder().addHeader("Authorization", bearer).build()
        return chain.proceed(chainRequest)
    }
}