package com.zoider.simpleapichecker.ui.request

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.zoider.simpleapichecker.R
import com.zoider.simpleapichecker.commons.HttpMethod
import com.zoider.simpleapichecker.database.request.HttpRequest

@Composable
fun RequestScreen(
    requestId: String,
    requestViewModel: RequestViewModel = hiltViewModel(),
) {
    val httpRequest by requestViewModel.get(requestId).observeAsState()
    httpRequest?.let {
        RequestScreenContent(
            httpRequest = it,
            onTestPressed = { requestViewModel.executeRequest(it) }
        )
    }
}

@Composable
fun RequestScreenContent(
    httpRequest: HttpRequest,
    onTestPressed: (httpRequest: HttpRequest) -> Unit
) {
    Column() {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(httpRequest.method.name)
            Text(httpRequest.url)
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier.padding(8.dp),
            onClick = { onTestPressed(httpRequest) }
        ) {
            Text(stringResource(id = R.string.test))
        }
    }
}

@Preview(name = "Request screen preview", showBackground = true)
@Composable
fun RequestScreenContentPreview() {
    RequestScreenContent(
        httpRequest = HttpRequest(method = HttpMethod.GET, url = "https://google.com"),
        onTestPressed = { }
    )
}