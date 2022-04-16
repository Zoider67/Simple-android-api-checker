package com.zoider.simpleapichecker.http

data class ApiCheckResult(
    val state: ApiState,
    val body: String
)
