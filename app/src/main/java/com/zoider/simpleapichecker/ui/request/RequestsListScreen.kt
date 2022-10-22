package com.zoider.simpleapichecker.ui.request

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.zoider.simpleapichecker.commons.HttpMethod
import com.zoider.simpleapichecker.database.request.HttpRequest
import com.zoider.simpleapichecker.ui.LeafScreen
import com.zoider.simpleapichecker.ui.components.Chip

@Composable
fun RequestsListScreen(
    navController: NavController,
    requestViewModel: RequestViewModel = hiltViewModel()
) {

    val requests: List<HttpRequest> by requestViewModel.httpRequests.observeAsState(listOf())
    RequestsListScreenContent(
        requests = requests,
        onNewRequestClicked = { navController.navigate(LeafScreen.CreateRequest.route) },
        onSelectRequest = {
            navController.navigate("${LeafScreen.RequestScreen.route}${it.id}")
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
    BoxWithConstraints(
        modifier = Modifier.fillMaxHeight()
    ) {
        LazyColumn() {
            items(requests) { query ->
                RequestListItem(
                    request = query,
                    onSelect = onSelectRequest,
                    onDelete = onDeleteRequest
                )
            }
        }
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp),
            onClick = onNewRequestClicked
        ) {
            Icon(Icons.Rounded.Add, contentDescription = "Add")
        }
    }
}

@Composable
fun RequestListItem(
    request: HttpRequest,
    onSelect: (httpRequest: HttpRequest) -> Unit = {},
    onDelete: (httpRequest: HttpRequest) -> Unit = {},
) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier.clickable { onSelect(request) },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Chip(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
                Text(request.method.name)
            }
            Text(request.url)
        }
        IconButton(
            onClick = { onDelete(request) }) {
            Icon(Icons.Rounded.Delete, contentDescription = "Delete", tint = Color.Red)
        }
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

@Preview(name = "Query List Item", showBackground = true)
@Composable
fun RequestListItemPreview() {
    RequestListItem(
        HttpRequest(method = HttpMethod.GET, url = "http://localhost/"),
    )
}
