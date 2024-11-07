package com.abachta.jetpacktutorial.ui

import androidx.annotation.StringRes
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.ChallengeId
import com.abachta.jetpacktutorial.data.CourseId
import com.abachta.jetpacktutorial.data.LessonId
import com.abachta.jetpacktutorial.data.QuizId
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
sealed class Screen {

    abstract val order: Int

    @StringRes
    abstract fun getTitleRes(): Int

    @Serializable
    data object Home : Screen() {

        @Transient
        override val order = 0

        override fun getTitleRes() =
            R.string.app_name
    }

    @Serializable
    data object Settings : Screen() {

        @Transient
        override val order = -1

        override fun getTitleRes() =
            R.string.settings
    }

    @Serializable
    data object SavedLessons : Screen() {

        @Transient
        override val order = 1

        override fun getTitleRes() = R.string.saved_lessons
    }

    @Serializable
    data class Course(
        @StringRes val titleResId: Int,
        @StringRes val descriptionResId: Int,
        val courseId: Int
    ) : Screen() {

        @Transient
        override val order = 2

        override fun getTitleRes() = titleResId

        @Transient
        val id = CourseId(courseId)
    }

    @Serializable
    data class Lesson(
        @StringRes val titleResId: Int,
        @StringRes val descriptionResId: Int,
        val lessonId: Int
    ) : Screen() {

        @Transient
        override val order = 3

        override fun getTitleRes() = titleResId

        @Transient
        val id = LessonId(lessonId)
    }

    @Serializable
    data class Quiz(
        @StringRes val titleResId: Int,
        val quizId: Int
    ) : Screen() {

        @Transient
        override val order = 4

        override fun getTitleRes() = titleResId

        @Transient
        val id = QuizId(quizId)
    }

    @Serializable
    data class Challenge(
        @StringRes val titleResId: Int,
        val challengeId: Int
    ) : Screen() {

        @Transient
        override val order = 5

        override fun getTitleRes() = titleResId

        @Transient
        val id = ChallengeId(challengeId)
    }

    @Serializable
    data class DeepLinkScreen(
        val number: Int
    ) : Screen() {

        @Transient
        override val order = 6

        override fun getTitleRes() = R.string.app_name
    }
}
