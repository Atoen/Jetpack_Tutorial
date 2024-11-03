package com.abachta.jetpacktutorial.ui

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.serialization.generateHashCode
import androidx.navigation.toRoute
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.CodeChallenge
import com.abachta.jetpacktutorial.data.Course
import com.abachta.jetpacktutorial.data.CourseId
import com.abachta.jetpacktutorial.data.Lesson
import com.abachta.jetpacktutorial.data.Quiz
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.serializer
import kotlin.reflect.KClass

data class ScreenData(
    @StringRes val title: Int,
    val order: Int
) {
    val isHome
        get() = order == 0
}

fun NavBackStackEntry?.getScreenData(): ScreenData {
    return when {
        this == null -> defaultScreen
        isOn<Screen.Home>() -> screenData<Screen.Home>()
        isOn<Screen.Settings>() -> screenData<Screen.Settings>()
        isOn<Screen.Course>() -> screenData<Screen.Course>()
        isOn<Screen.Lesson>() -> screenData<Screen.Lesson>()
        isOn<Screen.Quiz>() -> screenData<Screen.Quiz>()
        isOn<Screen.Challenge>() -> screenData<Screen.Challenge>()
        else -> defaultScreen
    }
}

private val defaultScreen = ScreenData(R.string.app_name, 0)

inline fun <reified T: Screen> NavBackStackEntry.isOn(): Boolean {
    return destination.hasRouteCached(T::class)
}

@SuppressLint("RestrictedApi")
@OptIn(InternalSerializationApi::class)
fun <T : Any> NavDestination.hasRouteCached(route: KClass<T>): Boolean {
    val routeHash = routeHashCodes.getOrPut(route) {
        route.serializer().generateHashCode()
    }
    return routeHash == id
}

private val routeHashCodes = mutableMapOf<KClass<*>, Int>()

private inline fun <reified T : Screen> NavBackStackEntry.screenData(): ScreenData {
    val screen = this.toRoute<T>()
    return ScreenData(
        title = screen.getTitleRes(),
        order = screen.order
    )
}

val slideInFromLeft = slideInHorizontally { -it }
val slideInFromRight = slideInHorizontally { it }

val slideOutToLeft = slideOutHorizontally { -it }
val slideOutToRight = slideOutHorizontally { it }

fun NavHostController.navigateToCourse(course: Course) {
    navigate(
        Screen.Course(
            titleResId = course.titleResId,
            descriptionResId = course.descriptionResId,
            courseId = course.id.value
        )
    )
}

fun NavHostController.navigateToLesson(lesson: Lesson, courseId: CourseId) {
    navigate(
        Screen.Lesson(
            titleResId = lesson.titleResId,
            descriptionResId = lesson.descriptionResId,
            courseId = courseId.value,
            lessonId = lesson.id.value
        )
    )
}

fun NavHostController.navigateToQuiz(quiz: Quiz) {
    navigate(
        Screen.Quiz(
            titleResId = quiz.titleResId,
            quizId = quiz.id.value
        )
    )
}

fun NavHostController.navigateToChallenge(challenge: CodeChallenge) {
    navigate(
        Screen.Challenge(
            titleResId = challenge.titleResId,
            challengeId = challenge.id.value
        )
    )
}
