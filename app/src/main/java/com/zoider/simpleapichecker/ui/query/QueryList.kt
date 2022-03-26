package com.zoider.simpleapichecker.ui.query

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
import com.zoider.simpleapichecker.database.query.HttpQuery
import com.zoider.simpleapichecker.ui.components.Chip

@Composable
fun QueriesList(queries: List<HttpQuery>) {
    LazyColumn() {
        items(queries) { query ->
            QueryListItem(query = query)
        }
    }
}

@Composable
fun QueryListItem(query: HttpQuery) {
    Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
        Chip(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
            Text(query.method.name)
        }
        Text(query.url)
    }
}

@Preview(name = "Query List Item", showBackground = true)
@Composable
fun QueryListItemPreview() {
    QueryListItem(HttpQuery(method = HttpMethod.GET, url = "http://localhost/"))
}

@Preview(name = "Queries List", showBackground = true)
@Composable
fun QueryListPreview() {
    QueriesList(
        listOf(
            HttpQuery(method = HttpMethod.GET, url = "http://localhost/"),
            HttpQuery(method = HttpMethod.GET, url = "http://google.com/"),
            HttpQuery(method = HttpMethod.GET, url = "http://youtube.com/")
        )
    )
}