package com.abachta.jetpacktutorial.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.abachta.jetpacktutorial.ui.AppTheme

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun JetpackTutorialTheme(
    appTheme: AppTheme = AppTheme.Auto,
    useDynamicColors: Boolean = true,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val useDarkTheme = when (appTheme) {
        AppTheme.Light -> false
        AppTheme.Dark -> true
        AppTheme.Auto -> isSystemInDarkTheme()
    }

    val colorScheme = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && useDynamicColors -> {
            if (useDarkTheme) dynamicDarkColorScheme(context)
            else dynamicLightColorScheme(context)
        }
        useDarkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}