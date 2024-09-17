package com.abachta.jetpacktutorial.ui.components

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.LocalLifecycleOwner
import kotlinx.coroutines.delay
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

sealed class BackNavigation {
    data object Idle : BackNavigation()
    data object FirstBack : BackNavigation()
}

@Composable
fun TwiceBackHandler(
    duration: Duration = 4.seconds,
    onFirstBack: () -> Unit,
    onSecondBack: () -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    var backState by remember { mutableStateOf<BackNavigation>(BackNavigation.Idle) }

    LaunchedEffect(lifecycleOwner.lifecycle, backState) {
        if (backState == BackNavigation.FirstBack) {
            delay(duration)
            backState = BackNavigation.Idle
        }
    }

    BackHandler(enabled = backState == BackNavigation.Idle) {
        backState = BackNavigation.FirstBack
        onFirstBack()
    }

    BackHandler(enabled = backState == BackNavigation.FirstBack) {
        onSecondBack()
    }
}