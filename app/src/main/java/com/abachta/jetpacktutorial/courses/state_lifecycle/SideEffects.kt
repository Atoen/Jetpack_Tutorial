package com.abachta.jetpacktutorial.courses.state_lifecycle

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.ui.components.CodeListing
import com.abachta.jetpacktutorial.ui.components.ResText

private val side_effects_1 = LessonPage (
    headingResId = R.string.side_effects_1_heading
) {

    ResText(R.string.side_effects_1_1)

    ResText(R.string.side_effects_1_2)

    CodeListing(
        code = """
            c-LaunchedEffect(keys) {
                delay(2000L)
                // do something
            }
        """.trimIndent()
    )
}

private val side_effects_2 = LessonPage (
    headingResId = R.string.side_effects_2_heading
) {

    ResText(R.string.side_effects_2_1)

    CodeListing(
        code = """
            val scope = c-rememberCoroutineScope()
            
            c-Button(onClick = {
                scope.launch {
                    // do something
                }
            }) {
                c-Text("Start operation")
            }
        """.trimIndent()
    )
}

private val side_effects_3 = LessonPage (
    headingResId = R.string.side_effects_3_heading
) {

    ResText(R.string.side_effects_3_1)

    CodeListing(
        code = """
            val lifecycleOwner = LocalLifecycleOwner.c-current
        
            c-DisposableEffect(lifecycleOwner) {
        
                val observer = LifecycleEventObserver { _, event ->
                    if (event == Lifecycle.Event.ON_START) {
                        // do something
                    }
                }
        
                lifecycleOwner.lifecycle.addObserver(observer)
        
                onDispose {
                    lifecycleOwner.lifecycle.removeObserver(observer)
                }
            }
        """.trimIndent()
    )
}

private val side_effects_4 = LessonPage (
    headingResId = R.string.side_effects_4_heading
) {

    ResText(R.string.side_effects_4_1)

    CodeListing(
        code = """
            @Composable
            fun rememberUserSettings(user: User): UserSettings {
                val settings = c-remember {
                    UserSettings()
                }
            
                c-SideEffect {
                    settings.setUserStatus(user.status)
                }
                
                return settings
            }
        """.trimIndent()
    )
}

private val side_effects_5 = LessonPage (
    headingResId = R.string.side_effects_5_heading
) {

    ResText(R.string.side_effects_5_1)

    CodeListing(
        code = """
            @Composable
            fun SplashScreen(onFinished: () -> Unit) {
                val currentOnFinished by c-rememberUpdatedState(onFinished)

                c-LaunchedEffect(true) {
                    delay(SplashScreenDuration)
                    currentOnTimeout()
                }
                
                // screen content
            }
        """.trimIndent()
    )
}

val sideEffectPages = listOf(
    side_effects_1,
    side_effects_2,
    side_effects_3,
    side_effects_4,
    side_effects_5,
)