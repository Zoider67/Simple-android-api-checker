package com.zoider.simpleapichecker.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.zoider.simpleapichecker.ui.LeafScreen
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
        navigation(startDestination = LeafScreen.RequestsList.route, route = Screen.Request.route) {
            composable(LeafScreen.RequestsList.route) {
                RequestsListScreen(
                    navController = navController,
                )
            }
            composable(LeafScreen.CreateRequest.route) {
                CreateRequestScreen(
                    navController = navController,
                )
            }
            //TODO: add nav arguments as parameters of LeafScreen class. Maybe function to return List<navArgumant>???
            composable(
                "${LeafScreen.RequestScreen.route}{requestId}",
                arguments = listOf(navArgument("requestId") { type = NavType.StringType })
            ) { backStackEntry ->
                RequestScreen(backStackEntry.arguments?.getString("requestId").toString())
            }
        }
        navigation(startDestination = LeafScreen.TasksList.route, route = Screen.Task.route) {
            composable(LeafScreen.TasksList.route) {
                TasksScreen(
                    navController = navController,
                )
            }
            composable(LeafScreen.CreateTask.route) {
                CreateTaskScreen(
                    navController = navController,
                )
            }
        }
    }
}