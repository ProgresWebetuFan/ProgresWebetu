package com.zako.webetu.auth.user.di

import com.zako.webetu.auth.user.data.UserRepositoryImpl
import com.zako.webetu.auth.user.data.local.UserLocalDataSourceImpl
import com.zako.webetu.auth.user.data.remote.UserRemoteDataSourceImpl
import com.zako.webetu.auth.user.model.UserLocalDataSource
import com.zako.webetu.auth.user.model.UserRemoteDataSource
import com.zako.webetu.auth.user.model.UserRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val userModule = module {
    singleOf(::UserLocalDataSourceImpl).bind(UserLocalDataSource::class)
    singleOf(::UserRemoteDataSourceImpl).bind(UserRemoteDataSource::class)
    singleOf(::UserRepositoryImpl).bind(UserRepository::class)
}
