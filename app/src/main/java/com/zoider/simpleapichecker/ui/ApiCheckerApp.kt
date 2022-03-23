package com.zoider.simpleapichecker.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.zoider.simpleapichecker.ui.query.QueriesScreen
import com.zoider.simpleapichecker.ui.task.TasksScreen
import com.zoider.simpleapichecker.ui.theme.ApiCheckerTheme

@Composable
fun ApiCheckerApp() {
    ApiCheckerTheme {
        val navItems = listOf(Screen.Queries, Screen.Tasks)
        val navController = rememberNavController()
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
                composable(Screen.Queries.route) { QueriesScreen() }
                composable(Screen.Tasks.route) { TasksScreen() }
            }
        }
    }
}