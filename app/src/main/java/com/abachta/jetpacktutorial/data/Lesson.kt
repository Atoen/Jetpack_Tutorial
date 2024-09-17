package com.abachta.jetpacktutorial.data

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable

class Lesson(
    @StringRes val titleResId: Int,
    @StringRes val descriptionResId: Int,
    val pages: List<LessonPage>,
    val progress: LessonProgress = LessonProgress(pages.count()),
    val id: Int = LessonId.next()
)

sealed class LessonId {
    companion object {
        private var current: Int = 0

        fun next() = current++
    }
}

class LessonPage(
    val content: @Composable () -> Unit
)