package com.zoider.simpleapichecker.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.PopUpToBuilder
import androidx.navigation.compose.*
import com.zoider.simpleapichecker.R
import com.zoider.simpleapichecker.ui.theme.ApiCheckerNavGraph
import com.zoider.simpleapichecker.ui.theme.ApiCheckerTheme

@Composable
fun ApiCheckerApp() {
    ApiCheckerTheme {
        val navItems = listOf(Screen.Request, Screen.Task)
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        Scaffold(
            topBar = {
                val currentScreen = getByRoute(navBackStackEntry?.destination?.route)
                TopAppBar(
                    title = { Text(stringResource(id = currentScreen.label)) },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                Icons.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.back)
                            )
                        }
                    }
                )
            },
            bottomBar = {
                BottomNavigation {
                    navItems.forEach { screen ->
                        BottomNavigationItem(
                            selected = navBackStackEntry?.destination?.route == screen.route,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = false
                                        inclusive = true
                                    }
                                    launchSingleTop = true
                                    restoreState = false
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
            ApiCheckerNavGraph(
                modifier = Modifier.padding(innerPadding),
                navController = navController
            )
        }
    }
}