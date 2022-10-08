package com.zoider.simpleapichecker

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler

object ExceptionHandler {
    val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
        throw exception
    }
}