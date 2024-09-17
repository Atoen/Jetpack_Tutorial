package com.abachta.jetpacktutorial

import android.graphics.Color
import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.abachta.jetpacktutorial.ui.AppTheme
import com.abachta.jetpacktutorial.ui.components.AppLayout
import com.abachta.jetpacktutorial.ui.theme.JetpackTutorialTheme
import com.abachta.jetpacktutorial.viewmodels.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<SettingsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().setKeepOnScreenCondition {
            !viewModel.isReady.value
        }

        setContent {
            val viewModel = hiltViewModel<SettingsViewModel>()
            JetpackTutorialTheme(
                appTheme = viewModel.theme
            ) {
                enableEdgeToEdge(appTheme = viewModel.theme)
                AppLayout(
                    viewModel = viewModel,
                    onExit = {
                        finishAffinity()
                    }
                )
            }
        }
    }

    private fun enableEdgeToEdge(appTheme: AppTheme) {
        val barStyle = when (appTheme) {
            AppTheme.Auto -> SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT)
            AppTheme.Light -> SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
            AppTheme.Dark -> SystemBarStyle.dark(Color.TRANSPARENT)
        }

        enableEdgeToEdge(
            statusBarStyle = barStyle,
            navigationBarStyle = barStyle
        )
    }
}