package com.abachta.jetpacktutorial.data

import androidx.annotation.StringRes
import com.abachta.jetpacktutorial.R

sealed class Course(
    @StringRes val titleResId: Int,
    @StringRes val descriptionResId: Int,
    val lessons: List<Lesson>,
    val progress : CourseProgress = CourseProgress(lessons.count())
) {
    val isCompleted: Boolean
        get() = progress.isCompleted

    data object GettingStarted : Course(
        titleResId = R.string.group_getting_started,
        descriptionResId = R.string.group_getting_started_description,
        lessons = listOf(
            Lesson.GettingStarted1,
            Lesson.GettingStarted2,
            Lesson.GettingStarted3
        )
    )

    data object ComposeEssentials  : Course(
        titleResId = R.string.TODO,
        descriptionResId = R.string.TODO,
        lessons = listOf(
            Lesson.GettingStarted1,
            Lesson.GettingStarted2,
            Lesson.GettingStarted3
        )
    )

    data object LayoutsAndUIElements : Course(
        titleResId = R.string.TODO,
        descriptionResId = R.string.TODO,
        lessons = listOf(
            Lesson.GettingStarted1,
            Lesson.GettingStarted2,
            Lesson.GettingStarted3
        )
    )

    data object ThemingAndStyling : Course(
        titleResId = R.string.TODO,
        descriptionResId = R.string.TODO,
        lessons = listOf(
            Lesson.GettingStarted1,
            Lesson.GettingStarted2,
            Lesson.GettingStarted3
        )
    )

    data object StateAndLifecycle : Course(
        titleResId = R.string.TODO,
        descriptionResId = R.string.TODO,
        lessons = listOf(
            Lesson.GettingStarted1,
            Lesson.GettingStarted2,
            Lesson.GettingStarted3
        )
    )

    data object InteractivityAndNavigation : Course(
        titleResId = R.string.TODO,
        descriptionResId = R.string.TODO,
        lessons = listOf(
            Lesson.GettingStarted1,
            Lesson.GettingStarted2,
            Lesson.GettingStarted3
        )
    )

    data object AnimationsAndTransitions : Course(
        titleResId = R.string.TODO,
        descriptionResId = R.string.TODO,
        lessons = listOf(
            Lesson.GettingStarted1,
            Lesson.GettingStarted2,
            Lesson.GettingStarted3
        )
    )

    data object AdvancedCompose : Course(
        titleResId = R.string.TODO,
        descriptionResId = R.string.TODO,
        lessons = listOf(
            Lesson.GettingStarted1,
            Lesson.GettingStarted2,
            Lesson.GettingStarted3
        )
    )
}

val Courses = listOf(
    Course.GettingStarted,
    Course.ComposeEssentials,
    Course.LayoutsAndUIElements,
    Course.ThemingAndStyling,
    Course.StateAndLifecycle,
    Course.InteractivityAndNavigation,
    Course.AnimationsAndTransitions,
    Course.AdvancedCompose
)