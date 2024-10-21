package com.abachta.jetpacktutorial.courses.styling

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.settings.AppTheme
import com.abachta.jetpacktutorial.settings.DynamicColorsOption
import com.abachta.jetpacktutorial.ui.components.CodeListing
import com.abachta.jetpacktutorial.ui.components.ImageLabelPosition
import com.abachta.jetpacktutorial.ui.components.LabeledImage
import com.abachta.jetpacktutorial.ui.components.ResText

private val app_theme_1 = LessonPage (
    headingResId = R.string.app_theme_1_heading
) {

    ResText(R.string.app_theme_1_1)

    ResText(R.string.app_theme_1_2)

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

    ResText(R.string.app_theme_2_1)

    ResText(R.string.app_theme_2_2)

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

    LabeledImage(
        imageResId = R.drawable.no_edge_to_edge,
        labelTextId = R.string.app_theme_no_e2e,
        labelPosition = ImageLabelPosition.Top,
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .width(250.dp)
    )

    LabeledImage(
        imageResId = R.drawable.edge_to_edge,
        labelTextId = R.string.app_theme_e2e,
        labelPosition = ImageLabelPosition.Top,
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .width(250.dp)
    )

    ResText(R.string.app_theme_3_1)

    ResText(R.string.app_theme_3_2)

    CodeListing(
        code = """
            dependencies {
                val activity_version = ...
                implementation("androidx.activity:activity-ktx:${'$'}activity_version")
            }
        """.trimIndent()
    )

    ResText(R.string.app_theme_3_3)

    CodeListing(
        code = """
            override fun onCreate(savedInstanceState: Bundle?) {
                enableEdgeToEdge()
                super.onCreate(savedInstanceState)
                
                setContent {
                    c-ApplicationTheme {
                        c-AppLayout()
                    }
                }
            }
        """.trimIndent()
    )
}

val appThemePages = listOf(
    app_theme_1,
    app_theme_2,
    app_theme_3
)