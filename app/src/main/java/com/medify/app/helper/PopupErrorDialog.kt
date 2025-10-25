package com.medify.app.helper

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.medify.app.R
import com.medify.app.designsystem.component.PopupDialog

@Composable
fun PopupErrorDialog(throwable: Throwable, onButtonClick: () -> Unit, onDismissRequest: () -> Unit) {
    return when (throwable.errorType()) {
        ApiErrorType.Network -> PopupDialog(
            title = stringResource(R.string.error_network_title),
            subtitle = stringResource(R.string.error_network_subtitle),
            buttonTitle = stringResource(R.string.button_ok),
            onButtonClick = onButtonClick,
            onDismissRequest = onDismissRequest,
        )

        ApiErrorType.Timeout -> PopupDialog(
            title = stringResource(R.string.error_timeout_title),
            subtitle = stringResource(R.string.error_timeout_subtitle),
            buttonTitle = stringResource(R.string.button_ok),
            onButtonClick = onButtonClick,
            onDismissRequest = onDismissRequest,
        )

        ApiErrorType.Unauthorized -> PopupDialog(
            title = stringResource(R.string.error_unauthorized_title),
            subtitle = stringResource(R.string.error_unauthorized_subtitle),
            buttonTitle = stringResource(R.string.button_ok),
            onButtonClick = onButtonClick,
            onDismissRequest = onDismissRequest,
        )

        ApiErrorType.Forbidden -> PopupDialog(
            title = stringResource(R.string.error_forbidden_title),
            subtitle = stringResource(R.string.error_forbidden_subtitle),
            buttonTitle = stringResource(R.string.button_ok),
            onButtonClick = onButtonClick,
            onDismissRequest = onDismissRequest,
        )

        ApiErrorType.NotFound -> PopupDialog(
            title = stringResource(R.string.error_not_found_title),
            subtitle = stringResource(R.string.error_not_found_subtitle),
            buttonTitle = stringResource(R.string.button_ok),
            onButtonClick = onButtonClick,
            onDismissRequest = onDismissRequest,
        )

        ApiErrorType.BadRequest -> PopupDialog(
            title = stringResource(R.string.error_bad_request_title),
            subtitle = stringResource(R.string.error_bad_request_subtitle),
            buttonTitle = stringResource(R.string.button_ok),
            onButtonClick = onButtonClick,
            onDismissRequest = onDismissRequest,
        )

        ApiErrorType.Server -> PopupDialog(
            title = stringResource(R.string.error_server_title),
            subtitle = stringResource(R.string.error_server_subtitle),
            buttonTitle = stringResource(R.string.button_ok),
            onButtonClick = onButtonClick,
            onDismissRequest = onDismissRequest,
        )

        ApiErrorType.Unknown -> PopupDialog(
            title = stringResource(R.string.error_unknown_title),
            subtitle = stringResource(R.string.error_unknown_subtitle),
            buttonTitle = stringResource(R.string.button_ok),
            onButtonClick = onButtonClick,
            onDismissRequest = onDismissRequest,
        )
    }
}