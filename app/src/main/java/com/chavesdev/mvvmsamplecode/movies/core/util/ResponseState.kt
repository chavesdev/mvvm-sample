package com.chavesdev.mvvmsamplecode.movies.core.util

sealed class ResponseState<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : ResponseState<T>(data)
    class Loading<T>(data: T? = null) : ResponseState<T>()
    class Error<T>(message: String) : ResponseState<T>(message = message)
}
