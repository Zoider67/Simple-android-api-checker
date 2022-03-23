package com.zoider.simpleapichecker.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Http
import androidx.compose.material.icons.filled.Task
import androidx.compose.ui.graphics.vector.ImageVector
import com.zoider.simpleapichecker.R

sealed class Screen(val route: String, @StringRes val label: Int, val icon: ImageVector) {
    object Queries : Screen("queries", R.string.queries, Icons.Filled.Http)
    object Tasks : Screen("tasks", R.string.tasks, Icons.Filled.Task)
}