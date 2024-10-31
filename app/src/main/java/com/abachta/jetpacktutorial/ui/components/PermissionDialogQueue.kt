package com.abachta.jetpacktutorial.ui.components

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.runtime.Composable
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale

@Composable
fun PermissionDialogQueue(
    activity: Activity,
    queue: List<String>,
    onDialogDismiss: () -> Unit,
) {

    queue.reversed().forEach { permission ->
        PermissionDialog(
            permissionTextProvider = DefaultPermissionTextProvider(permission),
            isPermanentlyDeclined = !shouldShowRequestPermissionRationale(
                activity,
                permission
            ),
            onDismiss = onDialogDismiss,
            onOkClick = onDialogDismiss,
            onGoToAppSettingsClick = activity::openAppSettings
        )
    }
}

fun Activity.openAppSettings() {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null)
    ).also(::startActivity)
}