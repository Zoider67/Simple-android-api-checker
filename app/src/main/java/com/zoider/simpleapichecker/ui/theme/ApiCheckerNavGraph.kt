package com.zoider.simpleapichecker.ui.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.zoider.simpleapichecker.ui.Screen
import com.zoider.simpleapichecker.ui.request.CreateRequestScreen
import com.zoider.simpleapichecker.ui.request.RequestScreen
import com.zoider.simpleapichecker.ui.request.RequestsListScreen
import com.zoider.simpleapichecker.ui.task.CreateTaskScreen
import com.zoider.simpleapichecker.ui.task.TasksScreen

@Composable
fun ApiCheckerNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = "task",
    ) {
        navigation(startDestination = Screen.RequestsList.route, route = "request") {
            composable(Screen.RequestsList.route) {
                RequestsListScreen(
                    navController = navController,
                )
            }
            composable(Screen.CreateRequest.route) {
                CreateRequestScreen(
                    navController = navController,
                )
            }
            composable(Screen.Request.route) {
                RequestScreen(
                    navController = navController
                )
            }
        }
        navigation(startDestination = Screen.TasksList.route, route = "task") {
            composable(Screen.TasksList.route) {
                TasksScreen(
                    navController = navController,
                )
            }
            composable(Screen.CreateTask.route) {
                CreateTaskScreen(
                    navController = navController,
                )
            }
        }
    }
}