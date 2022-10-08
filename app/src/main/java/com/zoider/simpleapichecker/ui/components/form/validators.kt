package com.zoider.simpleapichecker.ui.components.form

import android.content.Context
import com.zoider.simpleapichecker.R
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import java.util.regex.Pattern

private const val URL_VALIDATION_REGEX =
    "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]"

class UrlValidationState(val context: Context) :
    TextValidationState(context = context, validator = ::isUrlValid, onError = ::urlError)

private fun isUrlValid(url: String): Boolean {
    return url.isNotEmpty() && url.toHttpUrlOrNull() != null
//    return Pattern.matches(URL_VALIDATION_REGEX, url)
}

private fun urlError(context: Context?, text: String): String? {
    return context?.getString(R.string.invalid_url)
}