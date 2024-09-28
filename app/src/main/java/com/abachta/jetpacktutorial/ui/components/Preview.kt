package com.abachta.jetpacktutorial.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.magnifier
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

@Composable
fun Preview(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    var magnifierCenter by remember { mutableStateOf(Offset.Unspecified) }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .border(2.dp, MaterialTheme.colorScheme.tertiaryContainer)
            .padding(8.dp)
            .magnifier(
                sourceCenter = { magnifierCenter },
                size = DpSize(150.dp, 100.dp),
                cornerRadius = 8.dp,
                elevation = 8.dp,
                zoom = 2.0f
            )
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { magnifierCenter = it },
                    onDrag = { _, delta -> magnifierCenter += delta },
                    onDragEnd = { magnifierCenter = Offset.Unspecified },
                    onDragCancel = { magnifierCenter = Offset.Unspecified }
                )
            }
    ) {
        content()
    }
}