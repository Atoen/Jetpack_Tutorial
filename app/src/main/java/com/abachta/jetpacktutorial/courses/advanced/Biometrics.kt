package com.abachta.jetpacktutorial.courses.advanced

import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.abachta.jetpacktutorial.BiometricPromptManager.BiometricResult
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.LessonPage

private val biometrics_1 = LessonPage (
//    headingResId = R.string.biometrics_1_heading
) {

    val biometricResult by it.biometricResults.collectAsStateWithLifecycle(
        initialValue = null
    )

    val enrollLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = {

        }
    )

    LaunchedEffect(biometricResult) {
        if (biometricResult is BiometricResult.AuthenticationNotSet) {
            if (Build.VERSION.SDK_INT >= 30) {
                val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                    putExtra(
                        Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                        BIOMETRIC_STRONG or DEVICE_CREDENTIAL
                    )
                }

                enrollLauncher.launch(enrollIntent)
            }
        }
    }

    Button(onClick = {
        it.showBiometricsPrompt(
            title = "Sample prompt",
            description = "Sample desc",
            negativeButtonText = "Cancel"
        )
    }) {
        Text("Auth")
    }

    biometricResult?.let { result ->
        Text(
            text = when(result) {
                is BiometricResult.AuthenticationError -> {
                    result.error
                }
                BiometricResult.AuthenticationFailed -> {
                    stringResource(R.string.biometrics_auth_failed)
                }
                BiometricResult.AuthenticationNotSet -> {
                    stringResource(R.string.biometrics_not_set)
                }
                BiometricResult.AuthenticationSuccess -> {
                    stringResource(R.string.biometrics_auth_success)
                }
                BiometricResult.FeatureUnavailable -> {
                    stringResource(R.string.biometrics_feature_unavailable)
                }
                BiometricResult.HardwareUnavailable -> {
                    stringResource(R.string.biometrics_hardware_unavailable)
                }
            }
        )
    }
}

val biometricsPages = listOf(
    biometrics_1
)