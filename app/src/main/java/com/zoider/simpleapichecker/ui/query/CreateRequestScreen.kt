package com.zoider.simpleapichecker.ui.query

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.zoider.simpleapichecker.R
import com.zoider.simpleapichecker.database.HttpMethod
import com.zoider.simpleapichecker.database.query.HttpRequest
import com.zoider.simpleapichecker.ui.Screen
import com.zoider.simpleapichecker.ui.components.Select

@Composable
fun CreateRequestScreen(navController: NavController, requestViewModel: RequestViewModel) {
    CreateRequestScreenContent(
        onBackPressed = { navController.navigate(Screen.Queries.route) },
        onSavePressed = { httpQuery ->
            requestViewModel.create(httpQuery)
            navController.navigate(Screen.Queries.route)
        },
        onTestPressed = {
            requestViewModel.executeRequest(it)
        }
    )
}

@Composable
fun CreateRequestScreenContent(
    onBackPressed: () -> Unit,
    onSavePressed: (httpRequest: HttpRequest) -> Unit,
    onTestPressed: (httpRequest: HttpRequest) -> Unit,
) {
    var httpMethod by remember { mutableStateOf(HttpMethod.GET) }
    var url by remember { mutableStateOf("") }

    Column() {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = onBackPressed) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = stringResource(R.string.back))
                }
            },
            title = { Text(stringResource(R.string.new_http_request)) }
        )
        Row(modifier = Modifier.padding(8.dp)) {
            Select(
                modifier = Modifier.padding(end = 8.dp),
                values = HttpMethod.values().asList(),
                onSelect = { method -> httpMethod = method }
            ) {
                Text(it.name)
            }
            TextField(value = url, onValueChange = { url = it })
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(modifier = Modifier.padding(8.dp),
                onClick = { onTestPressed(HttpRequest(method = httpMethod, url = url)) }) {
                Text(text = stringResource(R.string.test))
            }
            Button(
                modifier = Modifier.padding(8.dp),
                onClick = { onSavePressed(HttpRequest(method = httpMethod, url = url)) }) {
                Text(text = stringResource(R.string.save))
            }
        }
    }
}

@Preview(name = "Create http request preview", showBackground = true)
@Composable
fun CreateRequestScreenPreview() {
    CreateRequestScreenContent(
        onBackPressed = { },
        onSavePressed = { },
        onTestPressed = { }
    )
}