package com.abachta.jetpacktutorial.ui.components

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.runtime.Composable
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import com.abachta.jetpacktutorial.PermissionModel

@Composable
fun PermissionDialogQueue(
    activity: Activity,
    queue: List<PermissionModel>,
    onDialogDismiss: () -> Unit,
) {

    queue.reversed().forEach { permissionModel ->
        PermissionDialog(
            permissionTextProvider = PermissionTextProvider(permissionModel),
            isPermanentlyDeclined = !shouldShowRequestPermissionRationale(
                activity,
                permissionModel.name
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