package com.zoider.simpleapichecker.apichecker

data class ApiCheckResult(
    val state: ApiState,
    val body: String?
)
