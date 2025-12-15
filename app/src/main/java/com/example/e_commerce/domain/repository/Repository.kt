package com.example.e_commerce.domain.repository

import com.example.e_commerce.domain.entites.UsersEntity
import com.example.e_commerce.domain.resources.Resources


interface LocalRepo{
    suspend fun getUserName(userName : String) : Resources<UsersEntity>
    suspend fun insertUser(user : UsersEntity) : Resources<Unit>
    suspend fun updateUser(user : UsersEntity) : Resources<Unit>
    suspend fun deleteUser(user : UsersEntity) : Resources<Unit>
    suspend fun getAllUsers() : Resources<List<UsersEntity>>
}

interface RemoteRepo{
    suspend fun signIn(user : UsersEntity) : Resources<Unit>
    suspend fun signUp(user : UsersEntity) : Resources<Unit>
}