package com.example.e_commerce.domain.entites


data class AuthenticationResponseEntity(

    val message: String? = null,

    val user: UserEntity,

    val token: String? = null
)

data class UserEntity(
    val role: String? = null,

    val name: String? = null,

    val email: String? = null
)