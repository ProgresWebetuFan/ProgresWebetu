package com.zako.webetu.auth.user.data

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.left
import arrow.core.right
import com.zako.webetu.auth.user.model.UserAuth
import com.zako.webetu.auth.user.model.UserLocalDataSource
import com.zako.webetu.auth.user.model.UserRepository
import com.zako.webetu.errors.AppError
import com.zako.webetu.errors.AuthenticationErrors

class UserRepositoryImpl(
    private val localDataSource: UserLocalDataSource,
) : UserRepository {

    override suspend fun saveUserAuth(user: UserAuth): Either<AppError, Boolean> {
        return localDataSource.saveUserAuth(user)
    }

    override suspend fun getUserAuth(): Either<AppError, UserAuth> {
        return localDataSource.getUserAuth().flatMap { userAuth ->
            userAuth?.right() ?: AuthenticationErrors.NoDefaultUserException(error = null).left()
        }
    }
}
