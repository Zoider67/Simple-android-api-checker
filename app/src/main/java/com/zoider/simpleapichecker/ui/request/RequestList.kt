package com.zoider.simpleapichecker.ui.request

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zoider.simpleapichecker.commons.HttpMethod
import com.zoider.simpleapichecker.database.request.HttpRequest
import com.zoider.simpleapichecker.ui.components.Chip

@Composable
fun RequestsList(
    requests: List<HttpRequest>,
    onSelect: (httpRequest: HttpRequest) -> Unit = {},
    onDelete: (httpRequest: HttpRequest) -> Unit = {},
) {
    LazyColumn() {
        items(requests) { query ->
            RequestListItem(request = query, onSelect = onSelect, onDelete = onDelete)
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
        modifier = Modifier.padding(8.dp).fillMaxWidth(),
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

@Preview(name = "Query List Item", showBackground = true)
@Composable
fun RequestListItemPreview() {
    RequestListItem(
        HttpRequest(method = HttpMethod.GET, url = "http://localhost/"),
    )
}

@Preview(name = "Queries List", showBackground = true)
@Composable
fun RequestListPreview() {
    RequestsList(
        listOf(
            HttpRequest(method = HttpMethod.GET, url = "http://localhost/"),
            HttpRequest(method = HttpMethod.GET, url = "http://google.com/"),
            HttpRequest(method = HttpMethod.GET, url = "http://youtube.com/")
        ),
    )
}