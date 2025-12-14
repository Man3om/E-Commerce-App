package com.example.e_commerce.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.e_commerce.data.models.local.UsersDataModel

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    suspend fun getAll() : List<UsersDataModel>

    @Query("SELECT * FROM users WHERE username = :userName")
    suspend fun getUserName(userName : String) : UsersDataModel?

    @Insert
    suspend fun insert(user : UsersDataModel)

    @Delete
    suspend fun delete(user : UsersDataModel)

    @Update
    suspend fun update(user : UsersDataModel)
}