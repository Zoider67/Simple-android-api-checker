package com.zoider.simpleapichecker.api

data class ApiCheckResult(
    val state: ApiState,
    val body: String
)
