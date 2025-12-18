package com.example.e_commerce.data.api

import com.example.e_commerce.data.models.remote.AuthenticationResponseModel
import com.example.e_commerce.data.models.remote.SignInRequest
import com.example.e_commerce.data.models.remote.SignUpRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface WebServices{
    @POST("auth/signin")
    suspend fun signIn(@Body signInRequest: SignInRequest) : Response<AuthenticationResponseModel>

    @POST("auth/signup")
    suspend fun signUp(@Body signUpRequest: SignUpRequest) : Response<AuthenticationResponseModel>
}