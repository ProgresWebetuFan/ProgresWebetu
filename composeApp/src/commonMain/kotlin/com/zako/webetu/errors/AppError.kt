package com.zako.webetu.errors

import com.zako.webetu.utils.BaseString

/**
 * Base class for all the errors in teh app
 *
 * All the errors should implement this interface
 */
interface AppError {
    var errorMessage: BaseString?
    var error: Throwable?
}

// /**
// * Get the message of the error
// *
// * @param context the context of the app
// * @return the message of the error
// */
// fun AppError.getMessage(context: Context): String? {
//    return this.errorMessage?.asString(context)
// }
//
// /**
// * Set the message of the error
// *
// * @param message the message of the error
// * @return the error with the message
// */
// fun AppError.setMessage(message: BaseString): AppError {
//    this.errorMessage = message
//    return this
// }
//
// /**
// * Get the error of the error
// *
// * @return the error of the error
// */
// fun AppError.setError(error: Throwable): AppError {
//    this.error = error
//    return this
// }
//
// fun AppError.getError(): Throwable? {
//    return this.error
// }
