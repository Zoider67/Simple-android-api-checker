package com.zoider.simpleapichecker.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.primary,
    text: @Composable () -> Unit
) {
    Surface(
        modifier = Modifier
            .then(modifier)
            .background(color = color, shape = RoundedCornerShape(12.dp))
            .padding(4.dp),
        color = color
    ) {
        text()
    }
}

@Preview(name = "Custom material design chip", showBackground = true)
@Composable
fun ChipPreview() {
    Chip { Text("test") }
}

@Preview(name = "Custom material design chip (long text)", showBackground = true)
@Composable
fun ChipLongTextPreview() {
    Chip(modifier = Modifier.padding(8.dp)) { Text("Lorem ipsum dolor sit amet") }
}