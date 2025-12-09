package com.example.e_commerce.data.models.local

import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nonnull

@Entity (tableName = "users")
data class UsersDataModel(
    val name: String?,
    @PrimaryKey
    val username: String,
    val email: String?,
    val password: Int?,
    val phone: String?
)
