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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.zoider.simpleapichecker.R
import com.zoider.simpleapichecker.commons.HttpMethod
import com.zoider.simpleapichecker.database.request.HttpRequest
import com.zoider.simpleapichecker.ui.Screen

@Composable
fun RequestsListScreen(
    navController: NavController,
    requestViewModel: RequestViewModel = hiltViewModel(
        navController.getBackStackEntry("request")
    )
) {

    val requests: List<HttpRequest> by requestViewModel.httpRequests.observeAsState(listOf())
    RequestsListScreenContent(
        requests = requests,
        onNewRequestClicked = { navController.navigate(Screen.CreateRequest.route) },
        onSelectRequest = {
            requestViewModel.select(it)
            navController.navigate(Screen.Request.route)
        },
        onDeleteRequest = {
            requestViewModel.delete(it)
        }
    )
}

@Composable
fun RequestsListScreenContent(
    onNewRequestClicked: () -> Unit,
    onSelectRequest: (httpRequest: HttpRequest) -> Unit,
    onDeleteRequest: (httpRequest: HttpRequest) -> Unit,
    requests: List<HttpRequest>
) {
    Column() {
        TopAppBar(
            title = { Text(stringResource(id = R.string.requests_title)) },
            actions = {
                IconButton(onClick = onNewRequestClicked) {
                    Icon(Icons.Filled.Add, contentDescription = "Add new query")
                }
            }
        )
        RequestsList(requests = requests, onSelect = onSelectRequest, onDelete = onDeleteRequest)
    }
}

@Preview(name = "Queries screen content preview", showBackground = true)
@Composable
fun RequestsScreenContentPreview() {
    RequestsListScreenContent(
        requests = listOf(
            HttpRequest(method = HttpMethod.GET, url = "http://localhost/"),
            HttpRequest(method = HttpMethod.GET, url = "http://google.com/"),
            HttpRequest(method = HttpMethod.GET, url = "http://youtube.com/")
        ),
        onNewRequestClicked = {},
        onSelectRequest = {},
        onDeleteRequest = {}
    )
}