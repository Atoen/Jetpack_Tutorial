package com.abachta.jetpacktutorial.data

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

class Lesson(
    @StringRes val titleResId: Int,
    @StringRes val descriptionResId: Int,
    val pages: List<LessonPage>,
    val progress: LessonProgress = LessonProgress(),
    val id: Int = LessonId.next()
) {
    val isCompleted
        get() = progress.completed

    fun complete() = progress.complete()
}

sealed class LessonId {
    companion object {
        private var current: Int = 0

        fun next() = current++
    }
}

class LessonProgress {

    var completed by mutableStateOf(false)
        private set

    fun complete() {
        completed = true
    }

    fun reset() {
        completed = false
    }
}

class LessonPage(
    val content: @Composable () -> Unit
)