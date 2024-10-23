package com.abachta.jetpacktutorial.viewmodels

import com.abachta.jetpacktutorial.BiometricPromptManager
import com.abachta.jetpacktutorial.settings.AppTheme
import com.abachta.jetpacktutorial.settings.DynamicColorsOption
import kotlinx.coroutines.flow.Flow

interface AppFeatureAccessor {

    var theme: AppTheme

    var dynamicColors: DynamicColorsOption

    fun showBiometricsPrompt(
        title: String,
        description: String,
        negativeButtonText: String
    )

    val biometricResults: Flow<BiometricPromptManager.BiometricResult>
}