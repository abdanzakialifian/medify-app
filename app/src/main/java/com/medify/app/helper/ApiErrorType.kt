package com.medify.app.helper

sealed interface ApiErrorType {
    data object Network : ApiErrorType
    data object Timeout : ApiErrorType
    data object Unauthorized : ApiErrorType
    data object Forbidden : ApiErrorType
    data object NotFound : ApiErrorType
    data object BadRequest : ApiErrorType
    data object Server : ApiErrorType
    data object Unknown : ApiErrorType
}