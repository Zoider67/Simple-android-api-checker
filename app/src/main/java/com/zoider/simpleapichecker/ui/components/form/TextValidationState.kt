package com.zoider.simpleapichecker.ui.components.form

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

open class TextValidationState(
    private val context: Context?,
    private val validator: (String) -> Boolean = { true },
    private val onError: (Context?, String) -> String? = { _, _ -> "" }
) {
    var text: String by mutableStateOf("")

    // was the TextField ever focused
    var isOnceFocused: Boolean by mutableStateOf(false)
    var isFocused: Boolean by mutableStateOf(false)
    private var displayErrors: Boolean by mutableStateOf(false)

    val isValid: Boolean
        get() = validator(text)

    fun onFocusChange(focused: Boolean) {
        isFocused = focused
        Log.d("TextValidationState.onFocusChange: ", "focused: $isFocused")
        if (focused) isOnceFocused = true
    }

    fun enableShowErrors() {
        // only show errors if the text was at least once focused
        Log.d("TextValidationState.enableShowErrors: ", "is once focused: $isOnceFocused")
        if (isOnceFocused) {
            displayErrors = true
        }
    }

    fun showErrors() = !isValid && displayErrors

    fun getError(): String? {
        Log.d("TextValidationState.getError: ", "valid: $isValid, display erros: $displayErrors")
        return if (!isValid && displayErrors) {
            onError(context, text)
        } else {
            null
        }
    }
}
