package com.abachta.jetpacktutorial.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp

@Composable
fun Preview(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    var zoomed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue =  if (zoomed) 1.5f else 1f,
        label = "preview_zoom"
    )

    val padding by animateDpAsState(
        targetValue = if (zoomed) 8.dp else 0.dp,
        label = "preview_padding"
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .padding(vertical = padding)
            .scale(scale)
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .clickable { zoomed = !zoomed }
            .padding(8.dp)
    ) {
        content()
    }
}