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

        val user = appDatabase.userDao().getUserName(userName)

        return if (user == null) {
            Log.d(TAG, "After getUserName: User not found")
            Resources.Error("User not found")
        } else {
            val entity = user.toEntity()
            Log.d(TAG, "After getUserName: User Found => $entity")
            Resources.Success(entity)
        }
    }

    override suspend fun insertUser(user: UsersEntity): Resources<Unit> {
        appDatabase.userDao().insert(user.toDataModel())
        return Resources.Success(Unit)
    }

    override suspend fun updateUser(user: UsersEntity): Resources<Unit> {
        appDatabase.userDao().update(user.toDataModel())
        return Resources.Success(Unit)
    }

    override suspend fun deleteUser(user: UsersEntity): Resources<Unit> {
        appDatabase.userDao().delete(user.toDataModel())
        return Resources.Success(Unit)
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