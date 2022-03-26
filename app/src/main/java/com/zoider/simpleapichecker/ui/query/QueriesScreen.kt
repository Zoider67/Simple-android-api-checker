package com.zoider.simpleapichecker.ui.query

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
import com.zoider.simpleapichecker.database.query.HttpQuery
import com.zoider.simpleapichecker.ui.Screen
import com.zoider.simpleapichecker.ui.components.Select

@Composable
fun QueriesScreen(navController: NavController, queryViewModel: QueryViewModel) {
    val queries: List<HttpQuery> by queryViewModel.httpQueries.observeAsState(listOf())
    QueriesScreenContent(
        queries = queries,
        onNewQueryClicked = { navController.navigate(Screen.CreateQuery.route) },
    )
}

@Composable
fun QueriesScreenContent(onNewQueryClicked: () -> Unit, queries: List<HttpQuery>) {
    Column() {
        TopAppBar(
            title = { Text(stringResource(id = R.string.queries_title)) },
            actions = {
                IconButton(onClick = onNewQueryClicked) {
                    Icon(Icons.Filled.Add, contentDescription = "Add new query")
                }
            }
        )
        QueriesList(queries = queries)
        Select<String>(values = listOf("One", "Two", "Three"), onSelect = { }) {
            Text(text = it)
        }
    }
}

@Preview(name = "Queries screen content preview", showBackground = true)
@Composable
fun QueriesScreenContentPreview() {
    QueriesScreenContent(
        queries = listOf(
            HttpQuery(method = HttpMethod.GET, url = "http://localhost/"),
            HttpQuery(method = HttpMethod.GET, url = "http://google.com/"),
            HttpQuery(method = HttpMethod.GET, url = "http://youtube.com/")
        ),
        onNewQueryClicked = {}
    )
}