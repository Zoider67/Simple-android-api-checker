package com.zoider.simpleapichecker.ui.consts

import android.graphics.Color
import com.zoider.simpleapichecker.R
import com.zoider.simpleapichecker.apichecker.ApiState

enum class UIApiState(val resTitle: Int, val resIcon: Int, val resColor: Int) {
    SUCCESS(
        R.string.api_state_online,
        R.drawable.baseline_check_circle_black_36,
        Color.GREEN
    ),
    NO_NETWORK(
        R.string.api_state_connection_error,
        R.drawable.baseline_help_black_36,
        Color.YELLOW
    ),
    ERROR(
        R.string.api_state_server_error,
        R.drawable.baseline_error_black_36,
        Color.RED
    );

    companion object {
        fun of(state: ApiState): UIApiState {
            return when (state) {
                ApiState.SUCCESS -> SUCCESS
                ApiState.NO_NETWORK -> NO_NETWORK
                else -> ERROR
            }
        }
    }
}