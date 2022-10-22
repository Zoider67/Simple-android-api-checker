package com.zoider.simpleapichecker.ui.task

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun TasksScreen(navController: NavController, taskViewModel: TaskViewModel = hiltViewModel()) {
    TasksScreenContent(navController, taskViewModel)
}

@Composable
fun TasksScreenContent(navController: NavController, taskViewModel: TaskViewModel) {
    Column() {
        Text("task list")
    }
}