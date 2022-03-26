package com.zoider.simpleapichecker.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


data class SelectState<T>(var expanded: Boolean, var selectedItem: T)

@Composable
fun <T> Select(
    modifier: Modifier = Modifier,
    values: List<T>,
    onSelect: (T) -> Unit,
    valueSlot: @Composable (value: T) -> Unit,
) {
    //var expanded by remember { mutableStateOf(false) }
    val (selectState, setSelectState) = remember {
        mutableStateOf(
            SelectState<T>(
                false,
                values.first()
            )
        )
    }
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .wrapContentSize(Alignment.TopStart)
//    ) {
        Button(modifier = modifier, onClick = {
            setSelectState(SelectState(expanded = true, selectedItem = selectState.selectedItem))
        }) {
            valueSlot(selectState.selectedItem)
        }
        DropdownMenu(
            expanded = selectState.expanded,
            onDismissRequest = { setSelectState(SelectState(expanded = false, selectedItem = selectState.selectedItem)) }) {
            values.forEach {
                DropdownMenuItem(onClick = {
                    setSelectState(SelectState(expanded = false, selectedItem = it))
                    onSelect(it)
                }) {
                    valueSlot(it)
                }
            }
        }
//    }
}

@Preview(name = "Select component preview", showBackground = true)
@Composable
fun SelectPreview() {
    Select<String>(values = listOf("One", "Two", "Three"), onSelect = { }) {
        Text(text = it)
    }
}