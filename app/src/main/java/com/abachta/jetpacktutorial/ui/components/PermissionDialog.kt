package com.abachta.jetpacktutorial.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PermissionDialog(
    permissionTextProvider: PermissionTextProvider,
    isPermanentlyDeclined: Boolean,
    onDismiss: () -> Unit,
    onOkClick: () -> Unit,
    onGoToAppSettingsClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    if (isPermanentlyDeclined) {
                        onGoToAppSettingsClick()
                        onDismiss()
                    } else {
                        onOkClick()
                    }
                }
            ) {
                Text(
                    text = if (isPermanentlyDeclined) {
                        "Grant permission"
                    } else {
                        "OK"
                    }
                )
            }
        },
        dismissButton = {
            if (isPermanentlyDeclined) {
                TextButton(onClick = onDismiss) {
                    Text(text = "Cancel")
                }
            }
        },
        title = {
            Text(text = "Permission required")
        },
        text = {
            Text(
                text = permissionTextProvider.getDescription(isPermanentlyDeclined)
            )
        },
        modifier = modifier
    )
}

interface PermissionTextProvider {
    fun getDescription(isPermanentlyDeclined: Boolean): String
}

class DefaultPermissionTextProvider(permission: String) : PermissionTextProvider {

    private val friendlyPermissionName by lazy { convertToFriendlyName(permission) }

    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if (isPermanentlyDeclined) {
            "It seems you permanently declined $friendlyPermissionName permission." +
                    "You can go to the app settings to grant it."
        } else {
            "This app needs access to $friendlyPermissionName."
        }
    }

    private fun convertToFriendlyName(permission: String): String {
        return permission
            .replace('_', ' ')
            .lowercase()
    }
}