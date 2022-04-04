package com.zoider.simpleapichecker.ui.request

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zoider.simpleapichecker.database.HttpMethod
import com.zoider.simpleapichecker.database.query.HttpRequest
import com.zoider.simpleapichecker.ui.components.Chip

@Composable
fun RequestsList(requests: List<HttpRequest>) {
    LazyColumn() {
        items(requests) { query ->
            RequestListItem(request = query)
        }
    }
}

@Composable
fun RequestListItem(request: HttpRequest) {
    Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
        Chip(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
            Text(request.method.name)
        }
        Text(request.url)
    }
}

@Preview(name = "Query List Item", showBackground = true)
@Composable
fun RequestListItemPreview() {
    RequestListItem(HttpRequest(method = HttpMethod.GET, url = "http://localhost/"))
}

@Preview(name = "Queries List", showBackground = true)
@Composable
fun RequestListPreview() {
    RequestsList(
        listOf(
            HttpRequest(method = HttpMethod.GET, url = "http://localhost/"),
            HttpRequest(method = HttpMethod.GET, url = "http://google.com/"),
            HttpRequest(method = HttpMethod.GET, url = "http://youtube.com/")
        )
    )
}