package com.abachta.jetpacktutorial

import android.Manifest
import androidx.annotation.StringRes

sealed class PermissionResult {
    data class Granted(val permission: PermissionModel) : PermissionResult()
    data class Denied(val permission: PermissionModel) : PermissionResult()
}

data class PermissionModel(
    val name: String,
    @StringRes val friendlyNameResId: Int
) {
    companion object {
        fun create(permission: String) = PermissionModel(
            name = permission,
            friendlyNameResId = getFriendlyName(permission)
        )
    }
}

@StringRes
fun getFriendlyName(permission: String): Int {
    return when (permission) {
        Manifest.permission.CAMERA -> R.string.camera_genitive
        Manifest.permission.RECORD_AUDIO -> R.string.microphone_genitive
        Manifest.permission.POST_NOTIFICATIONS -> R.string.notifications_genitive
        else -> R.string.ok
    }
}
