package com.example.e_commerce.data.di

import android.content.Context
import androidx.room.Room
import com.example.e_commerce.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseProvides {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context : Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "EcommerceDB"
        )
            .fallbackToDestructiveMigration(true)
            .build()
    }
}