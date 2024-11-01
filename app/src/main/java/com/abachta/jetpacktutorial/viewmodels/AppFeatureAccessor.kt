package com.abachta.jetpacktutorial.viewmodels

import com.abachta.jetpacktutorial.BiometricPromptManager
import com.abachta.jetpacktutorial.PermissionResult
import com.abachta.jetpacktutorial.settings.AppTheme
import com.abachta.jetpacktutorial.settings.DynamicColorsOption
import kotlinx.coroutines.flow.Flow

interface AppFeatureAccessor {

    var theme: AppTheme

    var dynamicColors: DynamicColorsOption

    fun showBiometricsPrompt(
        title: String,
        subtitle: String? = null,
        description: String? = null,
        negativeButtonText: String,
        allowDeviceCredentials: Boolean
    )

    val biometricResults: Flow<BiometricPromptManager.BiometricResult>

    val permissionResults: Flow<PermissionResult>

    fun requestPermission(permission: String)

    fun requestPermissions(permissions: Array<String>)

}