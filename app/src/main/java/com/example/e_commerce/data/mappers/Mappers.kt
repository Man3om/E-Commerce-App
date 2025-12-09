package com.example.e_commerce.data.mappers

import com.example.e_commerce.data.models.local.UsersDataModel
import com.example.e_commerce.domain.entites.UsersEntity

fun UsersDataModel.toEntity() =
    UsersEntity(name, username, email, password, phone)

fun UsersEntity.toDataModel() =
    UsersDataModel(name = name, username = username, email = email, password = password, phone = phone)