package com.example.e_commerce.data.dataSource.remote

import com.example.e_commerce.data.api.WebServices
import com.example.e_commerce.domain.entites.UsersEntity
import com.example.e_commerce.domain.repository.RemoteRepo
import com.example.e_commerce.domain.resources.Resources
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val webServices: WebServices) : RemoteRepo {
    override suspend fun signIn(user: UsersEntity): Resources<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun signUp(user: UsersEntity): Resources<Unit> {
        TODO("Not yet implemented")
    }
}