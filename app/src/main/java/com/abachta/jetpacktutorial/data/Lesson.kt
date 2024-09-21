package com.abachta.jetpacktutorial.data

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class Lesson(
    @StringRes val titleResId: Int,
    @StringRes val descriptionResId: Int,
    val pages: List<LessonPage>,
    val id: LessonId = LessonId.next(),
) {
    val progress = LessonProgress()

    val isCompleted
        get() = progress.completed

    val courseId = LessonCourseId(0)

    fun complete() = progress.complete()
}

data class LessonId(val value: Int) {

    fun next() = LessonId(value + 1)

    companion object {
        private var current: Int = 0

        fun next() = synchronized(this) {
            LessonId(current++)
        }
    }
}

data class LessonCourseId(var value: CourseId) {
    constructor(value: Int) : this(CourseId(value))
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