package com.zoider.simpleapichecker.ui.request

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.zoider.simpleapichecker.R
import com.zoider.simpleapichecker.commons.HttpMethod
import com.zoider.simpleapichecker.database.request.HttpRequest
import com.zoider.simpleapichecker.ui.LeafScreen
import com.zoider.simpleapichecker.ui.components.Select
import com.zoider.simpleapichecker.ui.components.form.CustomTextField
import com.zoider.simpleapichecker.ui.components.form.UrlValidationState

@Composable
fun CreateRequestScreen(
    navController: NavController,
    requestViewModel: RequestViewModel = hiltViewModel(navController.getBackStackEntry("request"))
) {
    CreateRequestScreenContent(
        onSavePressed = { httpQuery ->
            requestViewModel.create(httpQuery)
            navController.navigate(LeafScreen.RequestsList.route)
        },
        onTestPressed = {
            requestViewModel.executeRequest(it)
        }
    )
}

@Composable
fun CreateRequestScreenContent(
    onSavePressed: (httpRequest: HttpRequest) -> Unit,
    onTestPressed: (httpRequest: HttpRequest) -> Unit,
) {
    val context = LocalContext.current
    var httpMethod by remember { mutableStateOf(HttpMethod.GET) }
    val urlState = remember { UrlValidationState(context) }
    val urlFocusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Column() {
        Row(modifier = Modifier.padding(8.dp)) {
            Select(
                modifier = Modifier.padding(end = 8.dp),
                values = HttpMethod.values().asList(),
                onSelect = { method -> httpMethod = method }
            ) {
                Text(it.name)
            }
            CustomTextField(
                modifier = Modifier.focusRequester(urlFocusRequester),
                validationState = urlState,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(modifier = Modifier.padding(8.dp),
                onClick = {
                    onTestPressed(
                        HttpRequest(
                            method = httpMethod,
                            url = urlState.text
                        )
                    )
                }) {
                Text(text = stringResource(R.string.test))
            }
            Button(
                modifier = Modifier.padding(8.dp),
                onClick = {
                    onSavePressed(
                        HttpRequest(
                            method = httpMethod,
                            url = urlState.text
                        )
                    )
                }) {
                Text(text = stringResource(R.string.save))
            }
        }
    }
}

@Preview(name = "Create http request preview", showBackground = true)
@Composable
fun CreateRequestScreenPreview() {
    CreateRequestScreenContent(
        onSavePressed = { },
        onTestPressed = { }
    )
}