package com.jason.domain.core

sealed class AppResult<out T> {
    class Success<out T>(val data: T?) : AppResult<T>()
    class Failure(
        val exception: Exception,
        val errorCode: Int? = null,
        val errorKey: String = "",
        val message: String = ""
    ) : AppResult<Nothing>()
}