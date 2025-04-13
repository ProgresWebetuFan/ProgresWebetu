package com.zako.webetu.auth.user.model

import arrow.core.Either
import com.zako.webetu.errors.AppError

interface UserLocalDataSource {

    /**
     * save the user auth data to the local data source
     */
    suspend fun saveUserAuth(user: UserAuth): Either<AppError, Boolean>

    /**
     * get the user auth data form the local data source
     */
    suspend fun getUserAuth(): Either<AppError, UserAuth?>
}
