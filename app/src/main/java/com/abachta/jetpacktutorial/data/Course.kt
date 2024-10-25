package com.abachta.jetpacktutorial.data

import android.util.Log
import androidx.annotation.StringRes
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.courses.advanced.advancedLessons
import com.abachta.jetpacktutorial.courses.animations.animationsLessons
import com.abachta.jetpacktutorial.courses.getting_started.gettingStartedLessons
import com.abachta.jetpacktutorial.courses.jetpack_basics.jetpackBasicsLessons
import com.abachta.jetpacktutorial.courses.layout.layoutLessons
import com.abachta.jetpacktutorial.courses.navigation.navigationLessons
import com.abachta.jetpacktutorial.courses.state_lifecycle.stateLessons
import com.abachta.jetpacktutorial.courses.styling.styingLessons
import com.abachta.jetpacktutorial.data.models.CourseProgress

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
        titleResId = R.string.course_getting_started_title,
        descriptionResId = R.string.TODO,
        lessons = gettingStartedLessons,
    )

    data object ComposeBasics : Course(
        titleResId = R.string.course_compose_basics_title,
        descriptionResId = R.string.TODO,
        lessons = jetpackBasicsLessons,
    )

    data object Layout : Course(
        titleResId = R.string.course_layout_title,
        descriptionResId = R.string.TODO,
        lessons = layoutLessons,
    )

    data object Styling : Course(
        titleResId = R.string.course_theming_styling,
        descriptionResId = R.string.TODO,
        lessons = styingLessons,
    )

    data object StateAndLifecycle : Course(
        titleResId = R.string.course_state_lifecycle,
        descriptionResId = R.string.TODO,
        lessons = stateLessons,
    )

    data object Navigation : Course(
        titleResId = R.string.course_navigation,
        descriptionResId = R.string.TODO,
        lessons = navigationLessons,
    )

    data object Animations : Course(
        titleResId = R.string.course_animations,
        descriptionResId = R.string.TODO,
        lessons = animationsLessons,
    )

    data object Advanced : Course(
        titleResId = R.string.course_advanced,
        descriptionResId = R.string.TODO,
        lessons = advancedLessons,
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
    Course.Layout,
    Course.Styling,
    Course.StateAndLifecycle,
    Course.Navigation,
    Course.Animations,
    Course.Advanced
)

private val coursesMap = allCourses.associateBy { it.id }

fun getCourseById(courseId: CourseId): Course {
    return coursesMap.getOrElse(courseId) {
        Log.e(null, "Unable to retrieve course with id $courseId")
        allCourses.first()
    }
}