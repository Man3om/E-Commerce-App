package com.example.e_commerce.domain.repository

import com.example.e_commerce.domain.entites.AuthenticationResponseEntity
import com.example.e_commerce.domain.entites.UsersEntity
import com.example.e_commerce.domain.utils.apiResult.ApiResult
import com.example.e_commerce.domain.utils.resources.Resources


interface LocalRepo{
    suspend fun getUserName(userName : String) : Resources<UsersEntity>
    suspend fun insertUser(user : UsersEntity) : Resources<Unit>
    suspend fun updateUser(user : UsersEntity) : Resources<Unit>
    suspend fun deleteUser(user : UsersEntity) : Resources<Unit>
    suspend fun getAllUsers() : Resources<List<UsersEntity>>
}

interface RemoteRepo{
    suspend fun signIn(user : UsersEntity) : ApiResult<AuthenticationResponseEntity>
    suspend fun signUp(user : UsersEntity) : ApiResult<AuthenticationResponseEntity>
}