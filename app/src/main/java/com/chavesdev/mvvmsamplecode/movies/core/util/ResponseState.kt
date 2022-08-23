package com.chavesdev.mvvmsamplecode.movies.core.util

sealed class ResponseState(val data: Any? = null, val throwable: Throwable? = null) {
    class Success(data: Any) : ResponseState(data)
    object Ready : ResponseState()
    object Loading : ResponseState()
    class Error(throwable: Throwable) : ResponseState(throwable = throwable)
}
