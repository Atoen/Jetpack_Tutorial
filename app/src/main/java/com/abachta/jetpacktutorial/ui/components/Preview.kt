package com.abachta.jetpacktutorial.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Preview(
    modifier: Modifier = Modifier,
    showBorder: Boolean = true,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .then(if (showBorder) {
                    Modifier.border(2.dp, MaterialTheme.colorScheme.tertiaryContainer)
                } else Modifier)
            .padding(8.dp)
    ) {
        content()
    }
}

