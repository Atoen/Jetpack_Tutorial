package com.abachta.jetpacktutorial.data

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.abachta.jetpacktutorial.settings.AppTheme
import com.abachta.jetpacktutorial.settings.CodeListingFont

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

        const val START_ID = 1

        private var current: Int = START_ID

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

data class LessonPageOptions(
    val theme: AppTheme,
    val listingFont: CodeListingFont
)

class LessonPage(
    @StringRes
    val headingResId: Int? = null,
    val content: @Composable ColumnScope.(LessonPageOptions) -> Unit
)