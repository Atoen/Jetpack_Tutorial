package com.abachta.jetpacktutorial.ui

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
        @StringRes val descriptionResId: Int,
        val id: Int
    ) : Screen()

    @Serializable
    data class Lesson(
        @StringRes val titleResId: Int,
        @StringRes val descriptionResId: Int,
        val courseId: Int,
        val id: Int
    ) : Screen()
}
