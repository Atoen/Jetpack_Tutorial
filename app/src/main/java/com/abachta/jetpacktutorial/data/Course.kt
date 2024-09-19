package com.abachta.jetpacktutorial.data

import android.util.Log
import androidx.annotation.StringRes
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.lessons.gettingStartedLessons

sealed class Course(
    @StringRes val titleResId: Int,
    @StringRes val descriptionResId: Int,
    val lessons: List<Lesson>,
    val progress : CourseProgress = CourseProgress(lessons),
    val id: Int = CourseId.next()
) {
    val isCompleted: Boolean
        get() = progress.isCompleted

    data object GettingStarted : Course(
        titleResId = R.string.course_getting_started,
        descriptionResId = R.string.course_getting_started_description,
        lessons = gettingStartedLessons,
    )

    data object ComposeBasics : Course(
        titleResId = R.string.course_compose_basics,
        descriptionResId = R.string.TODO,
        lessons = listOf(),
    )

    data object LayoutsAndUIElements : Course(
        titleResId = R.string.course_layouts_elements,
        descriptionResId = R.string.TODO,
        lessons = listOf(),
    )

    data object ThemingAndStyling : Course(
        titleResId = R.string.course_theming_styling,
        descriptionResId = R.string.TODO,
        lessons = listOf(),
    )

    data object StateAndLifecycle : Course(
        titleResId = R.string.course_state_lifecycle,
        descriptionResId = R.string.TODO,
        lessons = listOf(),
    )

    data object InteractivityAndNavigation : Course(
        titleResId = R.string.course_interactivity_navigation,
        descriptionResId = R.string.TODO,
        lessons = listOf(),
    )

    data object AnimationsAndTransitions : Course(
        titleResId = R.string.course_animations_transitions,
        descriptionResId = R.string.TODO,
        lessons = listOf(),
    )

    data object AdvancedCompose : Course(
        titleResId = R.string.course_advanced,
        descriptionResId = R.string.TODO,
        lessons = listOf(),
    )
}

sealed class CourseId {
    companion object {
        private var current: Int = 0

        fun next() = current++
    }
}

val courses = listOf(
    Course.GettingStarted,
    Course.ComposeBasics,
    Course.LayoutsAndUIElements,
    Course.ThemingAndStyling,
    Course.StateAndLifecycle,
    Course.InteractivityAndNavigation,
    Course.AnimationsAndTransitions,
    Course.AdvancedCompose
)

private val coursesMap = courses.associateBy { it.id }

fun getCourseById(courseId: Int): Course {
    return coursesMap.getOrElse(courseId) {
        Log.e(null, "Unable to retrieve course with id $courseId")
        courses.first()
    }
}