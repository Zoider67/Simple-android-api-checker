package com.zoider.simpleapichecker.api

import android.graphics.Color
import com.zoider.simpleapichecker.R

enum class ApiState(val resTitle: Int, val resIcon: Int, val resColor: Int) {
    ONLINE(
        R.string.api_state_online,
        R.drawable.baseline_check_circle_black_36,
        Color.GREEN
    ),
    NO_NETWORK(
        R.string.api_state_connection_error,
        R.drawable.baseline_help_black_36,
        Color.YELLOW
    ),
    SERVER_IS_NOT_AVAILABLE(
        R.string.api_state_server_error,
        R.drawable.baseline_error_black_36,
        Color.RED)
}