package com.example.e_commerce.data.dataSource.local

import android.util.Log
import com.example.e_commerce.data.database.AppDatabase
import com.example.e_commerce.data.mappers.toDataModel
import com.example.e_commerce.data.mappers.toEntity
import com.example.e_commerce.domain.entites.UsersEntity
import com.example.e_commerce.domain.repository.LocalRepo
import com.example.e_commerce.domain.resources.Resources
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val appDatabase: AppDatabase) : LocalRepo {
    private val TAG = "LocalDataSourceImpl"
    override suspend fun getUserName(userName: String): Resources<UsersEntity> {
        Log.d(TAG, "At Start of getUserName: $userName")
        try {
            val user = appDatabase.userDao().getUserName(userName).toEntity()

            if (user.username.isEmpty()) {
                Log.d(TAG, "After getUserName: User not found")
                return Resources.Error("User not found")
            } else {
                Log.d(TAG, "After getUserName: user Found => $user")
                return Resources.Success(user)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return Resources.Error("Couldn't get user : User not found")
        }
    }

    override suspend fun insertUser(user: UsersEntity): Resources<Unit> {
        try {
            appDatabase.userDao().insert(user.toDataModel())
            return Resources.Success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            return Resources.Error("Couldn't insert user")
        }
    }

    override suspend fun updateUser(user: UsersEntity): Resources<Unit> {
        try {
            appDatabase.userDao().update(user.toDataModel())
            return Resources.Success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            return Resources.Error("Couldn't update user")
        }
    }

    override suspend fun deleteUser(user: UsersEntity): Resources<Unit> {
        try {
            appDatabase.userDao().delete(user.toDataModel())
            return Resources.Success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            return Resources.Error("Couldn't delete user")
        }
    }

    override suspend fun getAllUsers(): Resources<List<UsersEntity>> {
        val state = appDatabase.userDao().getAll().map {
            it.toEntity()
        }.let {
            if (it.isEmpty()) {
                Resources.Error("No users found")
            } else {
                Resources.Success(it)
            }
        }
        return state
    }
}