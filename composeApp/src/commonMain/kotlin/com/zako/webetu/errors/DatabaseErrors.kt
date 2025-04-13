package com.zako.webetu.errors

import com.zako.webetu.utils.BaseString
import webetu.composeapp.generated.resources.Res
import webetu.composeapp.generated.resources.database_delete_error
import webetu.composeapp.generated.resources.database_empty_error
import webetu.composeapp.generated.resources.database_error
import webetu.composeapp.generated.resources.database_insert_error
import webetu.composeapp.generated.resources.database_query_error
import webetu.composeapp.generated.resources.database_update_error

sealed class DatabaseErrors(
    override var errorMessage: BaseString? = BaseString.ResString(Res.string.database_error),
    override var error: Throwable?
) : AppError {

    data class DatabaseErrorException(
        override var errorMessage: BaseString? = BaseString.ResString(Res.string.database_error),
        override var error: Throwable?
    ) : DatabaseErrors(errorMessage = errorMessage, error)

    data class DatabaseEmptyException(
        override var errorMessage: BaseString? = BaseString.ResString(Res.string.database_empty_error),
        override var error: Throwable?
    ) : DatabaseErrors(errorMessage = errorMessage, error)

    data class DatabaseInsertException(
        override var errorMessage: BaseString? = BaseString.ResString(Res.string.database_insert_error),
        override var error: Throwable?
    ) : DatabaseErrors(errorMessage = errorMessage, error)

    data class DatabaseUpdateException(
        override var errorMessage: BaseString? = BaseString.ResString(Res.string.database_update_error),
        override var error: Throwable?
    ) : DatabaseErrors(errorMessage = errorMessage, error)

    data class DatabaseDeleteException(
        override var errorMessage: BaseString? = BaseString.ResString(Res.string.database_delete_error),
        override var error: Throwable?
    ) : DatabaseErrors(errorMessage = errorMessage, error)

    data class DatabaseQueryException(
        override var errorMessage: BaseString? = BaseString.ResString(Res.string.database_query_error),
        override var error: Throwable?
    ) : DatabaseErrors(errorMessage = errorMessage, error)
}
