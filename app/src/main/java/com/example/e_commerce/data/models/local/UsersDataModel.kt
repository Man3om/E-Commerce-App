package com.example.e_commerce.data.models.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "users")
data class UsersDataModel(
    val name: String?,
    @PrimaryKey
    val username: String,
    val email: String?,
    val password: String?,
    val phone: String?
)
