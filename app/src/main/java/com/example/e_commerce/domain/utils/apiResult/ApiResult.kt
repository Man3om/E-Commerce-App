package com.example.e_commerce.domain.utils.apiResult


sealed class ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error(val code: Int, val error: ErrorResponse?) : ApiResult<Nothing>()
}

data class ErrorResponse(
    val message: String?,
    val errorCode: String?
)