package com.zoider.simpleapichecker.apichecker

import com.zoider.simpleapichecker.apichecker.ApiState

data class ApiCheckResult(
    val stateUI: ApiState,
    val body: String?
)
