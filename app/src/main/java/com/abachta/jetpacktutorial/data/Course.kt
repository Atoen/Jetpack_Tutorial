package com.abachta.jetpacktutorial.data

import android.util.Log
import androidx.annotation.StringRes
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.courses.getting_started.gettingStartedLessons
import com.abachta.jetpacktutorial.courses.jetpack_basics.jetpackBasicsLessons

sealed class Course(
    @StringRes val titleResId: Int,
    @StringRes val descriptionResId: Int,
    val lessons: List<Lesson>,
    val id: CourseId = CourseId.next()
) {
    val progress = CourseProgress(lessons)

    val isCompleted: Boolean
        get() = progress.isCompleted

    init {
        lessons.forEach {
            it.courseId.value = id
        }
    }

    data object GettingStarted : Course(
        titleResId = R.string.course_getting_started,
        descriptionResId = R.string.course_getting_started_description,
        lessons = gettingStartedLessons,
    )

    data object ComposeBasics : Course(
        titleResId = R.string.course_compose_basics,
        descriptionResId = R.string.TODO,
        lessons = jetpackBasicsLessons,
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

@JvmInline
value class CourseId(val value: Int) {
    companion object {

        const val START_ID = 1

        private var current: Int = START_ID

        fun next() = synchronized(this) {
            CourseId(current++)
        }
    }
}

val allCourses = listOf(
    Course.GettingStarted,
    Course.ComposeBasics,
    Course.LayoutsAndUIElements,
    Course.ThemingAndStyling,
    Course.StateAndLifecycle,
    Course.InteractivityAndNavigation,
    Course.AnimationsAndTransitions,
    Course.AdvancedCompose
)

private val coursesMap = allCourses.associateBy { it.id }

fun getCourseById(courseId: CourseId): Course {
    return coursesMap.getOrElse(courseId) {
        Log.e(null, "Unable to retrieve course with id $courseId")
        allCourses.first()
    }
}