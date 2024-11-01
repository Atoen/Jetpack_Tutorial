package com.abachta.jetpacktutorial.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.abachta.jetpacktutorial.PermissionModel
import com.abachta.jetpacktutorial.R

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
                        stringResource(R.string.dialog_grant_permission)
                    } else {
                        stringResource(R.string.ok)
                    }
                )
            }
        },
        dismissButton = {
            if (isPermanentlyDeclined) {
                TextButton(onClick = onDismiss) {
                    Text(stringResource(R.string.dialog_cancel))
                }
            }
        },
        title = {
            Text(stringResource(R.string.dialog_permission_required))
        },
        text = {
            Text(
                text = permissionTextProvider.getDescription(isPermanentlyDeclined)
            )
        },
        modifier = modifier
    )
}

class PermissionTextProvider(private val permissionModel: PermissionModel) {

    private val friendlyName
        @Composable
        @ReadOnlyComposable
        get() = stringResource(permissionModel.friendlyNameResId)

    @Composable
    @ReadOnlyComposable
    fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if (isPermanentlyDeclined) {
            stringResource(R.string.permission_permanently_declined, friendlyName)
        } else {
            stringResource(R.string.action_needs_permission, friendlyName)
        }
    }
}