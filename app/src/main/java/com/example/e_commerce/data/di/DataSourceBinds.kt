package com.example.e_commerce.data.di

import com.example.e_commerce.data.dataSource.local.LocalDataSourceImpl
import com.example.e_commerce.data.dataSource.remote.RemoteDataSourceImpl
import com.example.e_commerce.domain.repository.LocalRepo
import com.example.e_commerce.domain.repository.RemoteRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceBinds {
    @Binds
    abstract fun bindLocalRepo(localDataSourceImpl: LocalDataSourceImpl): LocalRepo

    @Binds
    abstract fun bindRemoteRepo(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteRepo
}