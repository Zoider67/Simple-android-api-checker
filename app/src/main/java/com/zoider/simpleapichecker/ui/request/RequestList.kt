package com.zoider.simpleapichecker.ui.request

import androidx.compose.foundation.clickable
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
import com.zoider.simpleapichecker.database.request.HttpRequestEntity
import com.zoider.simpleapichecker.ui.components.Chip

@Composable
fun RequestsList(requestEntities: List<HttpRequestEntity>, onSelect: (id: Int) -> Unit = {}) {
    LazyColumn() {
        items(requestEntities) { query ->
            RequestListItem(requestEntity = query, onSelect = onSelect)
        }
    }
}

@Composable
fun RequestListItem(requestEntity: HttpRequestEntity, onSelect: (id: Int) -> Unit = {}) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onSelect(requestEntity.id) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Chip(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
            Text(requestEntity.method.name)
        }
        Text(requestEntity.url)
    }
}

@Preview(name = "Query List Item", showBackground = true)
@Composable
fun RequestListItemPreview() {
    RequestListItem(HttpRequestEntity(method = HttpMethod.GET, url = "http://localhost/"))
}

@Preview(name = "Queries List", showBackground = true)
@Composable
fun RequestListPreview() {
    RequestsList(
        listOf(
            HttpRequestEntity(method = HttpMethod.GET, url = "http://localhost/"),
            HttpRequestEntity(method = HttpMethod.GET, url = "http://google.com/"),
            HttpRequestEntity(method = HttpMethod.GET, url = "http://youtube.com/")
        ),
    )
}