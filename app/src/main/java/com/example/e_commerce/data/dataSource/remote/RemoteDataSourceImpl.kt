package com.example.e_commerce.data.dataSource.remote

import com.example.e_commerce.data.api.WebServices
import com.example.e_commerce.data.mappers.toEntity
import com.example.e_commerce.data.models.remote.SignInRequest
import com.example.e_commerce.data.models.remote.SignUpRequest
import com.example.e_commerce.data.utils.safeApiCall
import com.example.e_commerce.domain.entites.AuthenticationResponseEntity
import com.example.e_commerce.domain.entites.UsersEntity
import com.example.e_commerce.domain.repository.RemoteRepo
import com.example.e_commerce.domain.utils.apiResult.ApiResult
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val webServices: WebServices) : RemoteRepo {
    override suspend fun signIn(user: UsersEntity): ApiResult<AuthenticationResponseEntity> {
        val signInBody = SignInRequest(
            email = user.email,
            password = user.password
        )

        return safeApiCall(
            mapper = { it.toEntity() },
            apiCall = { webServices.signIn(signInBody) }
        )
    }

    override suspend fun signUp(user: UsersEntity): ApiResult<AuthenticationResponseEntity> {
        val signUpRequest = SignUpRequest(
            email = user.email,
            name = user.username,
            password = user.password,
            rePassword = user.password,
            phone = user.phone
        )

        return safeApiCall(
            mapper = { it.toEntity() },
            apiCall = { webServices.signUp(signUpRequest) }
        )
    }
}