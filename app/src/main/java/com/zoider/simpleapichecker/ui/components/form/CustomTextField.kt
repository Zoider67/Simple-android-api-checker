package com.zoider.simpleapichecker.ui.components.form

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    validationState: TextValidationState = remember { TextValidationState(null) },
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
) {
    Column() {
        TextField(
            modifier = Modifier
                .then(modifier)
                .onFocusChanged { focusState ->
                    Log.e("onFocusChanged: ", focusState.isFocused.toString())
                    validationState.onFocusChange(focusState.isFocused)
                    if (!focusState.isFocused) {
                        validationState.enableShowErrors()
                    }
                },
            value = validationState.text,
            onValueChange = {
                validationState.text = it
            },
            isError = validationState.showErrors(),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions
        )
        validationState.getError()
            ?.let { error -> Text(error, color = Color.Red) }
    }
}