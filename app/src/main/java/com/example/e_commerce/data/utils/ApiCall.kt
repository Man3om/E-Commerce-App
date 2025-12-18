package com.example.e_commerce.data.utils

import com.example.e_commerce.domain.utils.apiResult.ApiResult
import com.example.e_commerce.domain.utils.apiResult.ErrorResponse
import com.google.gson.Gson
import retrofit2.Response

suspend fun <T,R> safeApiCall(
    mapper : (T) -> R ,
    apiCall: suspend () -> Response<T>
): ApiResult<R> {
    return try {
        val response = apiCall()

        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                ApiResult.Success(mapper(body))
            } else {
                ApiResult.Error(
                    response.code(),
                    ErrorResponse("Empty response body", null)
                )
            }
        } else {
            val error = parseError(response)
            ApiResult.Error(response.code(), error)
        }
    } catch (e: Exception) {
        ApiResult.Error(
            -1,
            ErrorResponse(e.message ?: "Network error", null)
        )
    }
}

private fun parseError(response: Response<*>): ErrorResponse? {
    return try {
        val gson = Gson()
        response.errorBody()?.charStream()?.use { reader ->
            gson.fromJson(reader, ErrorResponse::class.java)
        }
    } catch (e: Exception) {
        null
    }
}
