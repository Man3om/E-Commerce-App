package com.example.e_commerce.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.e_commerce.data.models.local.UsersDataModel

@Database(entities = [UsersDataModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}