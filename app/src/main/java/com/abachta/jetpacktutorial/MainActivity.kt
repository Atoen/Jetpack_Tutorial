package com.abachta.jetpacktutorial

import android.graphics.Color
import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.abachta.jetpacktutorial.settings.AppTheme
import com.abachta.jetpacktutorial.settings.LocalAppTheme
import com.abachta.jetpacktutorial.settings.LocalCodeListingFont
import com.abachta.jetpacktutorial.ui.AppLayout
import com.abachta.jetpacktutorial.ui.components.PermissionDialogQueue
import com.abachta.jetpacktutorial.ui.theme.JetpackTutorialTheme
import com.abachta.jetpacktutorial.viewmodels.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<SettingsViewModel>()

    private val promptManager = BiometricPromptManager(this)

    private val permissionLauncher = registerForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        permissions.forEach { (permission, isGranted) ->
            val model = PermissionModel.create(permission)
            val result = if (isGranted) {
                PermissionResult.Granted(model)
            } else {
                PermissionResult.Denied(model)
            }

            viewModel.onPermissionResult(result)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.biometricPromptManager = promptManager
        viewModel.permissionRequestDispatcher = { permissionLauncher.launch(it) }

        installSplashScreen().setKeepOnScreenCondition {
            !viewModel.isReady.value
        }

        setContent {
            CompositionLocalProvider(
                LocalAppTheme provides viewModel.theme,
                LocalCodeListingFont provides viewModel.listingFont
            ) {
                JetpackTutorialTheme(
                    dynamicColors = viewModel.dynamicColors
                ) {
                    enableEdgeToEdge(appTheme = viewModel.theme)

                    PermissionDialogQueue(
                        activity = this,
                        queue = viewModel.visiblePermissionDialogQueue,
                        onDialogDismiss = viewModel::dismissDialog
                    )

                    AppLayout(
                        settingsViewModel = viewModel,
                        onExit = ::finishAffinity
                    )
                }
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