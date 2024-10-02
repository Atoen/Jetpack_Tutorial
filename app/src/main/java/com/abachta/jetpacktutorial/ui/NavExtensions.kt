package com.abachta.jetpacktutorial.ui

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Course
import com.abachta.jetpacktutorial.data.CourseId
import com.abachta.jetpacktutorial.data.Lesson
import com.abachta.jetpacktutorial.data.Quiz
import kotlin.reflect.KClass

@StringRes
fun NavBackStackEntry?.appBarTitle() : Int {
    if (this == null) return R.string.app_name

    if (isOn<Screen.Home>()) return R.string.app_name
    if (isOn<Screen.Settings>()) return R.string.settings

    if (isOn<Screen.Course>()) {
        return getCourseNameRes()
    }

    if (isOn<Screen.Lesson>()) {
        return getLessonNameRes()
    }

    if (isOn<Screen.Quiz>()) {
        return getQuizNameRes()
    }

    return R.string.app_name
}

inline fun <reified T: Screen> NavBackStackEntry.isOn(): Boolean =
    this.destination.hierarchy.any {
        it.hasRoute(T::class)
    }

@StringRes
private fun NavBackStackEntry.getCourseNameRes(): Int =
    this.toRoute<Screen.Course>().titleResId

@StringRes
private fun NavBackStackEntry.getLessonNameRes(): Int =
    this.toRoute<Screen.Lesson>().titleResId

@StringRes
private fun NavBackStackEntry.getQuizNameRes(): Int =
    this.toRoute<Screen.Quiz>().titleResId

fun <T : Screen> order(screen: KClass<T>): Int {
    return when (screen) {
        Screen.Home::class -> 0
        Screen.Settings::class -> -1
        Screen.Course::class -> 1
        Screen.Lesson::class -> 2
        else -> 0
    }
}

inline fun <reified T : Screen>
AnimatedContentTransitionScope<NavBackStackEntry>.enterTransition(): EnterTransition {
    var order = order(T::class)
    if (order == 0) order = if (targetState.isOn<Screen.Settings>()) -1 else 1

    return if (order > 0) {
        slideInFromLeft
    } else {
        slideInFromRight
    }
}

inline fun <reified T : Screen>
AnimatedContentTransitionScope<NavBackStackEntry>.exitTransition(): ExitTransition {
    var order = order(T::class)
    if (order == 0) order = if (targetState.isOn<Screen.Settings>()) -1 else 1

    return if (order < 0) {
        slideOutToLeft
    } else {
        slideOutToRight
    }
}

val slideInFromLeft = slideInHorizontally { -it }
val slideInFromRight = slideInHorizontally { it }

val slideOutToLeft = slideOutHorizontally { -it }
val slideOutToRight = slideOutHorizontally { it }

inline fun <reified T : Screen> NavGraphBuilder.slidingComposable(
    noinline content: @Composable (AnimatedContentScope.(NavBackStackEntry) -> Unit)
) {
    composable<T>(
        enterTransition = { enterTransition<T>() },
        popEnterTransition = { enterTransition<T>() },
        exitTransition = { exitTransition<T>()  },
        popExitTransition = { exitTransition<T>()  }
    ) {
        content(this, it)
    }
}

fun enterTransition(reversed: Boolean): EnterTransition {

    return if (reversed) {
        slideInHorizontally { it }
    } else {
        slideInHorizontally { -it }
    }
}

fun exitTransition(reversed: Boolean): ExitTransition {

    return if (reversed) {
        slideOutHorizontally { it }
    } else {
        slideOutHorizontally { it }
    }
}

fun NavHostController.navigateToCourse(course: Course) {
    navigate(
        Screen.Course(
            titleResId = course.titleResId,
            descriptionResId = course.descriptionResId,
            id = course.id.value
        )
    )
}

fun NavHostController.navigateToLesson(lesson: Lesson, courseId: CourseId) {
    navigate(
        Screen.Lesson(
            titleResId = lesson.titleResId,
            descriptionResId = lesson.descriptionResId,
            courseId = courseId.value,
            id = lesson.id.value
        )
    )
}

fun NavHostController.navigateToQuiz(quiz: Quiz) {
    navigate(
        Screen.Quiz(
            titleResId = quiz.titleResId,
            quizId = quiz.id
        )
    )
}
