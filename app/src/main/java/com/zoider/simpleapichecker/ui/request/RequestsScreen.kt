package com.zoider.simpleapichecker.ui.request

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.zoider.simpleapichecker.R
import com.zoider.simpleapichecker.database.HttpMethod
import com.zoider.simpleapichecker.database.query.HttpRequest
import com.zoider.simpleapichecker.ui.Screen

@Composable
fun RequestsScreen(navController: NavController, requestViewModel: RequestViewModel) {
    val requests: List<HttpRequest> by requestViewModel.httpRequests.observeAsState(listOf())
    RequestsScreenContent(
        requests = requests,
        onNewQueryClicked = { navController.navigate(Screen.CreateQuery.route) },
    )
}

@Composable
fun RequestsScreenContent(onNewQueryClicked: () -> Unit, requests: List<HttpRequest>) {
    Column() {
        TopAppBar(
            title = { Text(stringResource(id = R.string.requests_title)) },
            actions = {
                IconButton(onClick = onNewQueryClicked) {
                    Icon(Icons.Filled.Add, contentDescription = "Add new query")
                }
            }
        )
        RequestsList(requests = requests)
    }
}

@Preview(name = "Queries screen content preview", showBackground = true)
@Composable
fun RequestsScreenContentPreview() {
    RequestsScreenContent(
        requests = listOf(
            HttpRequest(method = HttpMethod.GET, url = "http://localhost/"),
            HttpRequest(method = HttpMethod.GET, url = "http://google.com/"),
            HttpRequest(method = HttpMethod.GET, url = "http://youtube.com/")
        ),
        onNewQueryClicked = {}
    )
}