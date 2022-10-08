package com.zoider.simpleapichecker.ui.task

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.zoider.simpleapichecker.R
import com.zoider.simpleapichecker.ui.Screen

@Composable
fun TasksScreen(navController: NavController, taskViewModel: TaskViewModel = hiltViewModel()) {
    TasksScreenContent(navController, taskViewModel)
}

@Composable
fun TasksScreenContent(navController: NavController, taskViewModel: TaskViewModel) {
    Column() {
        TopAppBar(
            title = { Text(text = stringResource(R.string.tasks_title)) },
            actions = {
                IconButton(onClick = {
                    navController.navigate(Screen.CreateTask.route)
                }) {
                    Icon(Icons.Filled.Add, contentDescription = "Create new task")
                }
            }
        )
    }
}