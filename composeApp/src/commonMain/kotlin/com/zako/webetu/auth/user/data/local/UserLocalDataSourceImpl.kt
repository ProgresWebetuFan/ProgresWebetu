package com.zako.webetu.auth.user.data.local

import arrow.core.Either
import com.zako.webetu.auth.user.model.UserAuth
import com.zako.webetu.auth.user.model.UserLocalDataSource
import com.zako.webetu.database.AppDatabase
import com.zako.webetu.errors.AppError
import com.zako.webetu.errors.DatabaseErrors

class UserLocalDataSourceImpl(
    private val database: AppDatabase
) : UserLocalDataSource {
    override suspend fun saveUserAuth(user: UserAuth): Either<AppError, Boolean> {
        return Either.catch {
            database.userAuthDao().insertUserAuth(user)
            true
        }.mapLeft {
            DatabaseErrors.DatabaseInsertException(error = it)
        }
    }

    override suspend fun getUserAuth(): Either<AppError, UserAuth?> {
        return Either.catch {
            database.userAuthDao().getDefaultUserAuth()
        }.mapLeft {
            DatabaseErrors.DatabaseQueryException(error = it)
        }
    }
}
