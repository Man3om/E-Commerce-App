package com.example.e_commerce.data.api

import com.example.e_commerce.data.models.remote.AuthenticationResponse
import com.example.e_commerce.data.models.remote.SignInRequest
import com.example.e_commerce.data.models.remote.SignUpRequest
import retrofit2.http.Body
import retrofit2.http.POST


interface WebServices{
    @POST("auth/signin")
    suspend fun signIn(@Body signInRequest: SignInRequest) : AuthenticationResponse

    @POST("auth/signup")
    suspend fun signIn(@Body signUpRequest: SignUpRequest) : AuthenticationResponse
}