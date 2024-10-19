package com.abachta.jetpacktutorial.courses.styling

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.settings.AppTheme
import com.abachta.jetpacktutorial.settings.DynamicColorsOption
import com.abachta.jetpacktutorial.ui.components.CodeListing

private val app_theme_1 = LessonPage (
    headingResId = R.string.app_theme_1_heading
) {

    var currentThemeIndex by remember { mutableIntStateOf(it.theme.value) }

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(onClick = {
            currentThemeIndex = (currentThemeIndex + 1) % 3
            it.theme = AppTheme.fromInt(currentThemeIndex)
        }) {
            val currentThemeText = it.theme.getText()
            Text(stringResource(R.string.app_theme_2_current, currentThemeText))
        }
    }

    CodeListing(
        code = """
            private val DarkColorScheme = darkColorScheme(
                // ...
            )

            private val LightColorScheme = lightColorScheme(
                // ...
            )
            
            @Composable
            fun AppTheme(
                useDarkTheme: Boolean = c-isSystemInDarkTheme(),
                content: @Composable () -> Unit
            ) {
                val colorScheme = when {
                    useDarkTheme -> DarkColorScheme
                    else -> LightColorScheme
                }
                
                c-MaterialTheme(
                    colorScheme = colorScheme,
                    content = content
                ) 
            }
        """.trimIndent()
    )
}

private val app_theme_2 = LessonPage (
    headingResId = R.string.app_theme_2_heading
) {

    var dynamicColorsEnabled by remember { mutableStateOf(it.dynamicColors.enabled) }

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(onClick = {
            dynamicColorsEnabled = !dynamicColorsEnabled
            it.dynamicColors = DynamicColorsOption.fromBoolean(dynamicColorsEnabled)
        }) {
            val dynamicColorsText = it.dynamicColors.getText()
            Text(stringResource(R.string.app_theme_3_current, dynamicColorsText))
        }
    }

    CodeListing(
        code = """
           @Composable
            fun AppTheme(
                useDarkTheme: Boolean = c-isSystemInDarkTheme(),
                useDynamicColors: Boolean = true,
                content: @Composable () -> Unit
            ) {
                val context = LocalContext.c-current
                val canUseDynamicColors = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
                val colorScheme = when {
                    canUseDynamicColors && useDynamicColors -> {
                        if (useDarkTheme) dynamicDarkColorScheme(context)
                        else dynamicLightColorScheme(context)
                    }
                    useDarkTheme -> DarkColorScheme
                    else -> LightColorScheme
                }
                
                c-MaterialTheme(
                    colorScheme = colorScheme,
                    content = content
                ) 
            }
        """.trimIndent()
    )
}

private val app_theme_3 = LessonPage (
    headingResId = R.string.app_theme_3_heading
) {

    CodeListing(
        code = """
            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                
                setContent {
                    c-ApplicationTheme(
                        useDarkTheme = ...,
                        useDynamicColors = ...
                    ) {
                        enableEdgeToEdge(useDarkTheme = ...)                     
                        c-AppLayout()
                    }
                }
            }
        """.trimIndent()
    )

    CodeListing(
        code ="""
            private fun enableEdgeToEdge(useDarkTheme: Boolean) {
                val barStyle = when (useDarkTheme) {
                    true -> SystemBarStyle.dark(Color.TRANSPARENT)
                    else -> SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
                }

                enableEdgeToEdge(
                    statusBarStyle = barStyle,
                    navigationBarStyle = barStyle
                )
            }
        """.trimIndent()
    )
}

val appThemePages = listOf(
    app_theme_1,
    app_theme_2,
    app_theme_3
)