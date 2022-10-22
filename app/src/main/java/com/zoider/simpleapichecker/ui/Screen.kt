package com.zoider.simpleapichecker.ui

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Http
import androidx.compose.material.icons.filled.Task
import androidx.compose.ui.graphics.vector.ImageVector
import com.zoider.simpleapichecker.R

sealed class Screen(val route: String, @StringRes val label: Int, val icon: ImageVector) {
    object Request : Screen("request", R.string.requests_title, Icons.Filled.Http)
    object Task : Screen("task", R.string.tasks, Icons.Filled.Task)
}

sealed class LeafScreen(val route: String, @StringRes val label: Int) {
    object RequestsList : LeafScreen("request/list", R.string.request_screen)
    object CreateRequest : LeafScreen("request/new", R.string.create_request)
    object RequestScreen : LeafScreen("request/", R.string.request_screen)
    object TasksList : LeafScreen("task/list", R.string.tasks)
    object CreateTask : LeafScreen("task/new", R.string.new_task)
}

fun getByRoute(route: String?): LeafScreen {
    Log.d("Screen get by route: ", route.toString())
    return when (route) {
        LeafScreen.RequestsList.route -> LeafScreen.RequestsList
        LeafScreen.CreateRequest.route -> LeafScreen.CreateRequest
        LeafScreen.RequestScreen.route -> LeafScreen.RequestScreen
        LeafScreen.TasksList.route -> LeafScreen.TasksList
        LeafScreen.CreateTask.route -> LeafScreen.CreateTask
        else -> LeafScreen.RequestsList
    }
}