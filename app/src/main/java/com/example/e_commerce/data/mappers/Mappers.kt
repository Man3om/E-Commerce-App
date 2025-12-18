package com.example.e_commerce.data.mappers

import com.example.e_commerce.data.models.local.UsersDataModel
import com.example.e_commerce.data.models.remote.AuthenticationResponseModel
import com.example.e_commerce.data.models.remote.UserModel
import com.example.e_commerce.domain.entites.AuthenticationResponseEntity
import com.example.e_commerce.domain.entites.UserEntity
import com.example.e_commerce.domain.entites.UsersEntity

fun UsersDataModel.toEntity() =
    UsersEntity(name, username, email, password, phone)

fun UsersEntity.toDataModel() =
    UsersDataModel(name = name, username = username, email = email, password = password, phone = phone)

fun UserModel.toEntity() =
    UserEntity(name = name, email = email, role = role)

fun UserEntity.toRemoteModel() =
    UserModel(name = name, email = email, role = role)

fun AuthenticationResponseModel.toEntity() =
    AuthenticationResponseEntity(message = message , user = user.toEntity() , token = token )

fun AuthenticationResponseEntity.toRemoteModel() =
    AuthenticationResponseModel(message = message , user = user.toRemoteModel() , token = token )
