package com.zako.webetu.auth.user.model

import arrow.core.Either
import com.zako.webetu.errors.AppError

interface UserRepository {
    /**
     save the user auth data to the local data source
     */
    suspend fun saveUserAuth(user: UserAuth): Either<AppError, Boolean>

    /**
     * get the user auth data form the local data source
     *
     * @return [com.zako.webetu.errors.AuthenticationErrors.NoDefaultUserException] if there is not user in the db
     *
     */
    suspend fun getUserAuth(): Either<AppError, UserAuth>
}
