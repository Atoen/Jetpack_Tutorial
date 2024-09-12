package com.abachta.jetpacktutorial

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.abachta.jetpacktutorial.ui.components.AppLayout
import com.abachta.jetpacktutorial.ui.components.CourseList
import com.abachta.jetpacktutorial.data.Courses
import com.abachta.jetpacktutorial.ui.AppTheme
import com.abachta.jetpacktutorial.ui.theme.JetpackTutorialTheme
import com.abachta.jetpacktutorial.viewmodels.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = hiltViewModel<SettingsViewModel>()
            JetpackTutorialTheme(
                appTheme = viewModel.theme
            ) {
                enableEdgeToEdge(appTheme = viewModel.theme)
                AppLayout(viewModel)
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