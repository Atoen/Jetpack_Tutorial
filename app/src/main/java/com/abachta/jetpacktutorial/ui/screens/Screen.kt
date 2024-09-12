package com.abachta.jetpacktutorial.ui.screens

import androidx.annotation.StringRes
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object Home : Screen()

    @Serializable
    data object Settings : Screen()

    @Serializable
    data class Course(
        @StringRes val titleResId: Int,
        @StringRes val descriptionResId: Int
    ) : Screen()

    @Serializable
    data class Lesson(
        @StringRes val titleResId: Int,
        @StringRes val descriptionResId: Int
    ) : Screen()
}
