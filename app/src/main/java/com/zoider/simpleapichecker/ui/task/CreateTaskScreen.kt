package com.zoider.simpleapichecker.ui.task

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.zoider.simpleapichecker.R
import com.zoider.simpleapichecker.ui.Screen

@Composable
fun CreateTaskScreen(navController: NavController, taskViewModel: TaskViewModel = hiltViewModel()) {
    CreateTaskScreenContent { navController.navigate(Screen.TasksList.route) }
}

@Composable
fun CreateTaskScreenContent(
    onBackPressed: () -> Unit,
) {
    Column() {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = {  }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = stringResource(R.string.back))
                }
            },
            title = { Text(stringResource(R.string.new_task)) }
        )
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Request:")

        }
    }
}

@Preview(name = "Create task screen")
@Composable
fun CreateTaskScreenPreview(){
    CreateTaskScreenContent({})
}