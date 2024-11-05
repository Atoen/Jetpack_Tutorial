package com.abachta.jetpacktutorial.courses.advanced

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.ui.components.CodeListing
import com.abachta.jetpacktutorial.ui.components.ItemizationMode
import com.abachta.jetpacktutorial.ui.components.ItemizedList
import com.abachta.jetpacktutorial.ui.components.ListItem.Companion.toTextItem
import com.abachta.jetpacktutorial.ui.components.ResText
import com.abachta.jetpacktutorial.ui.components.openAppSettings

private val permissions_1 = LessonPage (
   headingResId = R.string.permissions_1_heading
) {

    ResText(R.string.permissions_1_1)

    ResText(R.string.permissions_1_2)

    ResText(R.string.permissions_1_3)

    ResText(R.string.permissions_1_4)

    CodeListing(
        code = """
            <manifest ...>
                <uses-permission android:name="android.permission.CAMERA"/>
                <uses-permission android:name="android.permission.INTERNET" />
                
                <application ...>
                    ...
                </application>
            </manifest>
        """.trimIndent()
    )
}

private val permissions_2 = LessonPage (
   headingResId = R.string.permissions_2_heading
) {

    ResText(R.string.permissions_2_1)

    CodeListing(
        code = """
            <manifest ...>
                <application>
                    ...
                </application>
                <uses-feature android:name="android.hardware.camera"
                              android:required="false" />
            <manifest>
        """.trimIndent()
    )

    ResText(R.string.permissions_2_2)

    CodeListing(
        code = """
            if (applicationContext.packageManager.hasSystemFeature(
                    PackageManager.FEATURE_CAMERA_FRONT)) {
                // front camera available
            } else {
                // front camera not available
            }
        """.trimIndent()
    )
}

private fun hasCameraPermission(context: Context) =
    ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.CAMERA
    ) == PackageManager.PERMISSION_GRANTED

private val permissions_3 = LessonPage (
   headingResId = R.string.permissions_3_heading
) {

    ResText(R.string.permissions_3_1)

    CodeListing(
        code = """
            var hasPermission by c-remember { mutableStateOf(false) }
            
            val recordVideoLauncher = c-rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestPermission(),
                onResult = { isGranted ->
                    hasPermission = isGranted
                    if (isGranted) {
                        // Use the granted permission
                    } else {
                        // Explain to the user why the permission is required
                    }
                }
            )
            
            c-Button(onClick = {
                recordVideoLauncher.launch(
                    Manifest.permission.CAMERA
                )
            }) { ... }
        """.trimIndent()
    )

    val context = LocalContext.current
    var asked by remember { mutableStateOf(false) }

    var hasPermission by remember { mutableStateOf(hasCameraPermission(context)) }
    var isPermanentlyDeclined by remember { mutableStateOf(false) }

    val recordVideoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            asked = true
            hasPermission = isGranted

            if (!isGranted) {
                isPermanentlyDeclined = !ActivityCompat.shouldShowRequestPermissionRationale(
                    context as Activity,
                    Manifest.permission.CAMERA
                )
            }
        }
    )

    Column(
        modifier = Modifier.align(Alignment.CenterHorizontally),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
    ) {
        Button(onClick = {
            recordVideoLauncher.launch(Manifest.permission.CAMERA)
        }) {
            ResText(R.string.permission_3_request)
        }

        if (asked) {
            val text = if (hasPermission) {
                stringResource(R.string.permission_granted)
            } else stringResource(R.string.permission_declined)

            Text(text)

            if (isPermanentlyDeclined) {
                ResText(R.string.permission_declined_permanently)
            }
        }
    }
}

private val permissions_4 = LessonPage (
   headingResId = R.string.permissions_4_heading
) {

    ResText(R.string.permissions_4_1)

    CodeListing(
        code = """
            val hasPermission = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        """.trimIndent()
    )

    ResText(R.string.permissions_4_2)

    CodeListing(
        code = """
            ActivityCompat.shouldShowRequestPermissionRationale(
                context as Activity,
                Manifest.permission.CAMERA
            )
        """.trimIndent()
    )
}

private val permissions_5 = LessonPage (
   headingResId = R.string.permissions_5_heading
) {

    ResText(R.string.permissions_5_1)

    ItemizedList(
        R.string.permissions_5_2.toTextItem(),
        R.string.permissions_5_3.toTextItem(),
        R.string.permissions_5_4.toTextItem(),
        itemizationMode = ItemizationMode.Numeric
    )

    CodeListing(
        code = """
            when {
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.PERMISSION
                ) == PackageManager.PERMISSION_GRANTED -> {
                    // Use the granted permission
                }
                
                ActivityCompat.shouldShowRequestPermissionRationale(
                    activity,
                    Manifest.permission.PERMISSION
                ) {
                    // Explain why the permission is needed
                }
                
                else -> {
                    // Ask for the permission directly
                    requestPermissionLauncher.launch(
                        Manifest.permission.PERMISSION
                    )
                }
            }
        """.trimIndent()
    )
}

private val permissions_6 = LessonPage (
    headingResId = R.string.permissions_6_heading
) {

    ResText(R.string.permissions_6_1)

    ResText(R.string.permissions_6_2)

    CodeListing(
        code = """
            fun Activity.openAppSettings() {
                val intent = Intent(
                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.fromParts("package", packageName, null)
                )
                
                startActivity(intent)
            }
        """.trimIndent()
    )

    val context = LocalContext.current
    val activity = context as? Activity

    Button(
        onClick = { activity?.openAppSettings() },
        modifier = Modifier.align(Alignment.CenterHorizontally)
    ) {
        ResText(R.string.permissions_6_open_app_settings)
    }
}

val permissionsPages = listOf(
    permissions_1,
    permissions_2,
    permissions_3,
    permissions_4,
    permissions_5,
    permissions_6
)