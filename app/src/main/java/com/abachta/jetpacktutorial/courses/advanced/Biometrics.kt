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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.abachta.jetpacktutorial.BiometricPromptManager.BiometricResult
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.ui.components.CodeListing
import com.abachta.jetpacktutorial.ui.components.ResText

private val biometrics_1 = LessonPage (
    headingResId = R.string.biometrics_1_heading
) {

    CodeListing(
        code = """
            dependencies {
                ...
                implementation("androidx.biometric:biometric:1.1.0")
                implementation("androidx.appcompat:appcompat:1.6.1")
            }
        """.trimIndent()
    )

    CodeListing(
        code = """
            class MainActivity : AppCompatActivity() {
                override fun onCreate(...) {
                    ...
                }
            }
        """.trimIndent()
    )

    CodeListing(
        code = """
            <resources>
                <style
                    name="Theme.YourApp"
                    parent="Theme.AppCompat.DayNight.NoActionBar" />
            </resources>
        """.trimIndent()
    )

    CodeListing(
        code = """
            <manifest ...>
                ...
                
                <application
                    ...
                    android:theme="@style/Theme.YourApp">
                    ...
                </activity>
            </manifest>
        """.trimIndent()
    )
}

private val biometrics_2 = LessonPage (
    headingResId = R.string.biometrics_2_heading
) {

    CodeListing(
        code = """
            val activity: AppCompatActivity = ...
            val manager = BiometricManager.from(activity)
            
            val authenticators = BIOMETRIC_STRONG
            
            val promptInfo = PromptInfo.Builder()
                .setTitle(title)
                .setSubtitle(subtitle)
                .setDescription(description)
                .setAllowedAuthenticators(authenticators)
                
            if (Build.VERSION.SDK_INT < 30) {
                promptInfo.setNegativeButtonText(negativeButtonText)
            }
        """.trimIndent()
    )

    CodeListing(
        code = """
            when(manager.canAuthenticate(authenticators)) {
                BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                    // handle no hardware
                }
                BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                    // handle hardware busy
                }
                BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                    // handle no biometric auth set
                }
                else -> Unit
            }
        """.trimIndent()
    )
}

private val biometrics_3 = LessonPage (
    headingResId = R.string.biometrics_3_heading
) {

    CodeListing(
        code = """
            val prompt = BiometricPrompt(
                activity,
                object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationError(
                        errorCode: Int, errString: CharSequence
                    ) {
                        // handle auth error
                    }
    
                    override fun onAuthenticationSucceeded(
                        result: BiometricPrompt.AuthenticationResult
                    ) {
                        // handle auth success
                    }
    
                    override fun onAuthenticationFailed() {
                        // handle auth failed
                    }
                }
            )
    
            prompt.authenticate(promptInfo.build())
        """.trimIndent()
    )

    val title = stringResource(R.string.biometrics_prompt_title)
    val subtitle = stringResource(R.string.biometric_prompt_subtitle)
    val description = stringResource(R.string.biometrics_prompt_description)
    val negativeButtonText = stringResource(R.string.dialog_cancel)

    Button(
        modifier = Modifier.align(Alignment.CenterHorizontally),
        onClick = {
            it.showBiometricsPrompt(
                title = title,
                subtitle = subtitle,
                description = description,
                negativeButtonText = negativeButtonText,
                allowDeviceCredentials = false
            )
        }
    ) {
        ResText(R.string.biometrics_prompt_show)
    }
}

private val biometrics_4 = LessonPage (
    headingResId = R.string.biometrics_4_heading
) {

    CodeListing(
        code = """
            sealed interface BiometricResult {
                data object AuthenticationSuccess: BiometricResult
                data class AuthenticationError(val error: String): BiometricResult
                ...
            }
            
            private val resultChannel = Channel<BiometricResult>()
            val promptResults = resultChannel.receiveAsFlow()
            
            ...
            override fun onAuthenticationSucceeded(
                result: BiometricPrompt.AuthenticationResult
            ) {
                resultChannel.trySend(BiometricResult.AuthenticationSuccess)
            }
        """.trimIndent()
    )

    CodeListing(
        code = """
            val biometricResult by biometricResults.c-collectAsStateWithLifecycle(
                initialValue = null
            )
            
            biometricResult?.let { result ->
                when (result) {
                    ...
                }
            } 
        """.trimIndent()
    )

    val biometricResult by it.biometricResults.collectAsStateWithLifecycle(
        initialValue = null
    )

    val title = stringResource(R.string.biometrics_prompt_title)
    val subtitle = stringResource(R.string.biometric_prompt_subtitle)
    val description = stringResource(R.string.biometrics_prompt_description)
    val negativeButtonText = stringResource(R.string.dialog_cancel)

    Button(
        modifier = Modifier.align(Alignment.CenterHorizontally),
        onClick = {
            it.showBiometricsPrompt(
                title = title,
                subtitle = subtitle,
                description = description,
                negativeButtonText = negativeButtonText,
                allowDeviceCredentials = false
            )
        }
    ) {
        ResText(R.string.biometrics_prompt_show)
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

private val biometrics_5 = LessonPage (
    headingResId = R.string.biometrics_5_heading
) {

    CodeListing(
        code = """
            val authenticators = if (Build.VERSION.SDK_INT >= 30) {
                BIOMETRIC_STRONG or DEVICE_CREDENTIAL
            } else BIOMETRIC_STRONG
            
            val promptInfo = PromptInfo.Builder()
                .setTitle(title)
                .setAllowedAuthenticators(authenticators)
                
            if (Build.VERSION.SDK_INT < 30) {
                promptInfo.setNegativeButtonText(negativeButtonText)
            }
        """.trimIndent()
    )

    val biometricResult by it.biometricResults.collectAsStateWithLifecycle(
        initialValue = null
    )

    val title = stringResource(R.string.biometrics_prompt_title)
    val negativeButtonText = stringResource(R.string.dialog_cancel)

    Button(
        modifier = Modifier.align(Alignment.CenterHorizontally),
        onClick = {
            it.showBiometricsPrompt(
                title = title,
                negativeButtonText = negativeButtonText,
                allowDeviceCredentials = true
            )
        }
    ) {
        ResText(R.string.biometrics_prompt_show)
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

private val biometrics_6 = LessonPage (
    headingResId = R.string.biometrics_6_heading
) {

    CodeListing(
        code = """
            val biometricResult = ...
            
            val enrollLauncher = c-rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartActivityForResult(),
                onResult = {
                    // handle result
                }
            )
            c-LaunchedEffect(biometricResult) {
                if(biometricResult is BiometricResult.AuthenticationNotSet) {
                    if(Build.VERSION.SDK_INT >= 30) {
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
        """.trimIndent()
    )


    val biometricResult by it.biometricResults.collectAsStateWithLifecycle(
        initialValue = null
    )

    val enrollLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = {}
    )
    LaunchedEffect(biometricResult) {
        if(biometricResult is BiometricResult.AuthenticationNotSet) {
            if(Build.VERSION.SDK_INT >= 30) {
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

    val title = stringResource(R.string.biometrics_prompt_title)
    val description = stringResource(R.string.biometrics_prompt_description)
    val negativeButtonText = stringResource(R.string.dialog_cancel)

    Button(
        modifier = Modifier.align(Alignment.CenterHorizontally),
        onClick = {
            it.showBiometricsPrompt(
                title = title,
                description = description,
                negativeButtonText = negativeButtonText,
                allowDeviceCredentials = false
            )
        }
    ) {
        ResText(R.string.biometrics_prompt_show)
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
    biometrics_1,
    biometrics_2,
    biometrics_3,
    biometrics_4,
    biometrics_5,
    biometrics_6
)