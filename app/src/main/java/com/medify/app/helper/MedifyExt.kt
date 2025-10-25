package com.medify.app.helper

import android.util.Patterns
import io.ktor.client.plugins.ClientRequestException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Throwable.errorType(): ApiErrorType = when (this) {
    is UnknownHostException -> ApiErrorType.Network
    is SocketTimeoutException -> ApiErrorType.Timeout
    is ClientRequestException -> when (response.status.value) {
        400 -> ApiErrorType.BadRequest
        401 -> ApiErrorType.Unauthorized
        403 -> ApiErrorType.Forbidden
        404 -> ApiErrorType.NotFound
        in 500..599 -> ApiErrorType.Server
        else -> ApiErrorType.Unknown
    }

    else -> ApiErrorType.Unknown
}

fun String.isValidEmail(): Boolean = Patterns.EMAIL_ADDRESS.matcher(this.trim()).matches()