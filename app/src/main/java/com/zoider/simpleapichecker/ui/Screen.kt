package com.zoider.simpleapichecker.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Http
import androidx.compose.material.icons.filled.Task
import androidx.compose.ui.graphics.vector.ImageVector
import com.zoider.simpleapichecker.R

sealed class Screen(val route: String, @StringRes val label: Int, val icon: ImageVector) {
    //TODO: make common class for storing routes
    object Queries : Screen("queries", R.string.requests_title, Icons.Filled.Http)
    object CreateQuery : Screen("create_query", R.string.create_request, Icons.Filled.Add)
    object Tasks : Screen("tasks", R.string.tasks, Icons.Filled.Task)
}