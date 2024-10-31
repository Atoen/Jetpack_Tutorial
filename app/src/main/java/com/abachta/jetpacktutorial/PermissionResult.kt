package com.abachta.jetpacktutorial

sealed class PermissionResult {
    data class Granted(val permission: String) : PermissionResult()
    data class Denied(val permission: String) : PermissionResult()
}