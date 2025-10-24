package com.medify.app.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.medify.app.R
import com.medify.app.designsystem.theme.MedifyTheme

@Composable
fun MedifyInputField(
    value: String,
    placeholder: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    interactionSource: MutableInteractionSource? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background),
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            readOnly = readOnly,
            textStyle = TextStyle(
                fontFamily = FontFamily(Font(R.font.proximanova_semi_bold)),
                color = MaterialTheme.colorScheme.primary,
                fontSize = 12.sp,
            ),
            placeholder = {
                Text(
                    text = placeholder,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.proximanova_regular)),
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 12.sp,
                    )
                )
            },
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            maxLines = maxLines,
            minLines = minLines,
            interactionSource = interactionSource,
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                cursorColor = MaterialTheme.colorScheme.outline,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MedifyInputFieldPreview() {
    MedifyTheme {
        MedifyInputField(
            modifier = Modifier.padding(20.dp),
            value = "",
            onValueChange = {},
            placeholder = "Masukkan Email Anda"
        )
    }
}