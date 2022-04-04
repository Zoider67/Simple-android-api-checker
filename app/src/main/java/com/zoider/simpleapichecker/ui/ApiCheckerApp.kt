package com.zoider.simpleapichecker.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.zoider.simpleapichecker.AppContainer
import com.zoider.simpleapichecker.domain.ExecuteRequestUseCase
import com.zoider.simpleapichecker.notifications.NotificationCenter
import com.zoider.simpleapichecker.ui.request.CreateRequestScreen
import com.zoider.simpleapichecker.ui.request.RequestsScreen
import com.zoider.simpleapichecker.ui.request.RequestViewModel
import com.zoider.simpleapichecker.ui.task.TasksScreen
import com.zoider.simpleapichecker.ui.theme.ApiCheckerTheme

@Composable
fun ApiCheckerApp() {
    ApiCheckerTheme {
        val navItems = listOf(Screen.Queries, Screen.Tasks)
        val navController = rememberNavController()
        //TODO: move to DI container!!!
        val notificationCenter = NotificationCenter(LocalContext.current)
        Scaffold(
            bottomBar = {
                BottomNavigation {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    navItems.forEach { screen ->
                        BottomNavigationItem(
                            selected = navBackStackEntry?.destination?.route == screen.route,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    screen.icon,
                                    contentDescription = stringResource(id = screen.label)
                                )
                            },
                            label = { Text(stringResource(id = screen.label)) })
                    }
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Screen.Queries.route,
                Modifier.padding(innerPadding)
            ) {
                //TODO: navigation via viewmodel, or navigation manager
                val queryViewModel = RequestViewModel(
                    AppContainer.apiTesterRepository,
                    ExecuteRequestUseCase(notificationCenter = notificationCenter)
                )
                //TODO: common query screens state holder???
                composable(Screen.Queries.route) {
                    RequestsScreen(
                        navController = navController,
                        requestViewModel = queryViewModel
                    )
                }
                composable(Screen.CreateQuery.route) {
                    CreateRequestScreen(
                        navController = navController,
                        requestViewModel = queryViewModel
                    )
                }
                composable(Screen.Tasks.route) { TasksScreen(navController = navController) }
            }
        }
    }
}