package com.abachta.jetpacktutorial.ui

import androidx.annotation.StringRes
import com.abachta.jetpacktutorial.data.CourseId
import com.abachta.jetpacktutorial.data.LessonId
import com.abachta.jetpacktutorial.data.QuizId
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

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
        val courseId: Int
    ) : Screen() {
        @Transient
        val id: CourseId = CourseId(courseId)
    }

    @Serializable
    data class Lesson(
        @StringRes val titleResId: Int,
        @StringRes val descriptionResId: Int,
        val courseId: Int,
        val lessonId: Int
    ) : Screen() {
        @Transient
        val id: LessonId = LessonId(lessonId)
    }

    @Serializable
    data class Quiz(
        @StringRes val titleResId: Int,
        val quizId: Int
    ) : Screen() {
        @Transient
        val id: QuizId = QuizId(quizId)
    }
}
