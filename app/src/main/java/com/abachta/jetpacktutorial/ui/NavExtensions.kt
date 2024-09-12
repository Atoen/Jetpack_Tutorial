package com.abachta.jetpacktutorial.ui

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.ui.screens.Screen
import kotlin.reflect.KClass

@Composable
@ReadOnlyComposable
fun NavBackStackEntry?.appBarTitle() : Pair<String, Boolean> {
    if (this == null) return stringResource(R.string.app_name) to false

    if (isOn<Screen.Home>()) return stringResource(R.string.app_name) to true
    if (isOn<Screen.Settings>()) return stringResource(R.string.settings) to false

    if (isOn<Screen.Course>()) {
        return stringResource(getCourseName()) to false
    }

    return stringResource(R.string.app_name) to false
}

inline fun <reified T: Screen> NavBackStackEntry.isOn(): Boolean =
    this.destination.hierarchy.any {
        it.hasRoute(T::class)
    }

private fun NavBackStackEntry.getCourseName(): Int =
    this.toRoute<Screen.Course>().titleResId

fun <T : Screen> order(screen: KClass<T>): Int {
    return when (screen) {
        Screen.Home::class -> 0
        Screen.Settings::class -> -1
        Screen.Course::class -> 1
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
