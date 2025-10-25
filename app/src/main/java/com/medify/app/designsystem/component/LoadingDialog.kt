package com.medify.app.designsystem.component

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun LoadingDialog(properties: DialogProperties = DialogProperties(), onDismissRequest: () -> Unit = {}) {
    Dialog(
        properties = properties,
        onDismissRequest = onDismissRequest,
        content = {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun LoadingDialogPreview() {
    MaterialTheme {
        LoadingDialog()
    }
}