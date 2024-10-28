package com.abachta.jetpacktutorial.courses.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.ui.SnackbarController
import com.abachta.jetpacktutorial.ui.SnackbarEvent
import com.abachta.jetpacktutorial.ui.components.CodeListing
import com.abachta.jetpacktutorial.ui.components.Preview
import com.abachta.jetpacktutorial.ui.components.ResText
import com.abachta.jetpacktutorial.ui.components.TwiceBackHandler
import kotlinx.coroutines.launch

private val back_1 = LessonPage (
   headingResId = R.string.back_1_heading
) {

    ResText(R.string.back_1_1)

    CodeListing(
        code = """
            c-BackHandler {
                // do something on back
            }
        """.trimIndent()
    )

    ResText(R.string.back_1_2)

    val scope = rememberCoroutineScope()

    val message = stringResource(R.string.back_handled)

    BackHandler(
        enabled = isCurrentPage
    ) {
        scope.launch {
            SnackbarController.sendEvent(SnackbarEvent(
                message = message,
                duration = SnackbarDuration.Short
            ))
        }
    }
}

private val back_2 = LessonPage (
   headingResId = R.string.back_2_heading
) {

    ResText(R.string.back_2_1)

    CodeListing(
        code = """
            var text by c-remember { mutableStateOf("") }
            
            c-BackHandler(
                enabled = text.isNotBlank()
            ) {
                // do something on back
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {

        val scope = rememberCoroutineScope()
        var text by remember { mutableStateOf("") }

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            singleLine = true,
            placeholder = {
                Text("Type here")
            }
        )

        val message = stringResource(R.string.back_text_not_empty)

        BackHandler(
            enabled = text.isNotBlank() && this@LessonPage.isCurrentPage
        ) {
            scope.launch {
                SnackbarController.sendEvent(SnackbarEvent(
                    message = message,
                    duration = SnackbarDuration.Short
                ))
            }
        }
    }
}

private val back_3 = LessonPage (
   headingResId = R.string.back_3_heading
) {

    ResText(R.string.back_3_1)

    CodeListing(
        code = """
            enum class BackNavigation {
                Idle, FirstBack
            }
        """.trimIndent()
    )

    ResText(R.string.back_3_2)

    CodeListing(
        code = """
            @Composable
            fun TwiceBackHandler(
                duration: Duration,
                onFirstBack: () -> Unit,
                onSecondBack: () -> Unit
            ) { 
                val lifecycleOwner = LocalLifecycleOwner.c-current
                var backState by c-remember {
                    mutableStateOf(BackNavigation.Idle)
                }
                
                c-LaunchedEffect(lifecycleOwner.lifecycle, backState) {
                    if (backState == BackNavigation.FirstBack) {
                        delay(duration)
                        backState = BackNavigation.Idle
                    }
                }

                c-BackHandler(
                    enabled = backState == BackNavigation.Idle
                ) {
                    backState = BackNavigation.FirstBack
                    onFirstBack()
                }

                c-BackHandler(
                    enabled = backState == BackNavigation.FirstBack
                ) {
                    onSecondBack()
                }
            }
        """.trimIndent()
    )

    val scope = rememberCoroutineScope()
    val firstMessage = stringResource(R.string.back_3_first)
    val secondMessage = stringResource(R.string.back_3_second)

    if (isCurrentPage) {
        TwiceBackHandler(
            onFirstBack = {
                scope.launch {
                    SnackbarController.sendEvent(SnackbarEvent(
                        message = firstMessage,
                        duration = SnackbarDuration.Short
                    ))
                }
            },
            onSecondBack = {
                scope.launch {
                    SnackbarController.sendEvent(SnackbarEvent(
                        message = secondMessage,
                        duration = SnackbarDuration.Short
                    ))
                }
            }
        )
    }
}

val backHandlerPages = listOf(
    back_1,
    back_2,
    back_3
)