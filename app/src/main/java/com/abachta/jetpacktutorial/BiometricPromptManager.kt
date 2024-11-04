package com.abachta.jetpacktutorial

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.PromptInfo
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class BiometricPromptManager(
    private val activity: AppCompatActivity
) {

    private val resultFlow = MutableSharedFlow<BiometricResult>(
        replay = 1
    )
    val promptResults = resultFlow.asSharedFlow()

    fun showBiometricPrompt(
        title: String,
        subtitle: String? = null,
        description: String? = null,
        negativeButtonText: String,
        allowDeviceCredentials: Boolean
    ) {
        val manager = BiometricManager.from(activity)
        val authenticators = if (Build.VERSION.SDK_INT >= 30 && allowDeviceCredentials) {
            BIOMETRIC_STRONG or DEVICE_CREDENTIAL
        } else BIOMETRIC_STRONG

        when(manager.canAuthenticate(authenticators)) {
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                resultFlow.tryEmit(BiometricResult.HardwareUnavailable)
                return
            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                resultFlow.tryEmit(BiometricResult.FeatureUnavailable)
                return
            }
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                resultFlow.tryEmit(BiometricResult.AuthenticationNotSet)
                return
            }
            else -> Unit
        }

        val promptInfo = PromptInfo.Builder()
            .setTitle(title)
            .setDescription(description)
            .setAllowedAuthenticators(authenticators)
            .setSubtitle(subtitle)

        if (Build.VERSION.SDK_INT < 30 || !allowDeviceCredentials) {
            promptInfo.setNegativeButtonText(negativeButtonText)
        }

        val prompt = BiometricPrompt(
            activity,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    resultFlow.tryEmit(BiometricResult.AuthenticationError(errString.toString()))
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    resultFlow.tryEmit(BiometricResult.AuthenticationSuccess)
                }

                override fun onAuthenticationFailed() {
                    resultFlow.tryEmit(BiometricResult.AuthenticationFailed)
                }
            }
        )

        prompt.authenticate(promptInfo.build())
    }

    sealed interface BiometricResult {
        data object HardwareUnavailable: BiometricResult
        data object FeatureUnavailable: BiometricResult
        data class AuthenticationError(val error: String): BiometricResult
        data object AuthenticationFailed: BiometricResult
        data object AuthenticationSuccess: BiometricResult
        data object AuthenticationNotSet: BiometricResult
    }
}