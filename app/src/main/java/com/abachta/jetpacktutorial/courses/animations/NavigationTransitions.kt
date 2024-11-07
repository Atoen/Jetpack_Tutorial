package com.abachta.jetpacktutorial.courses.animations

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.ui.components.CodeListing
import com.abachta.jetpacktutorial.ui.components.Preview
import com.abachta.jetpacktutorial.ui.components.ResText
import kotlinx.serialization.Serializable

@Serializable
private object ScreenA

@Serializable
private object ScreenB

@Serializable
private object ScreenC

private enum class NavigationScreen {
    A, B, C
}

@Composable
private fun NavScreen(
    screen: NavigationScreen,
    onClick1: () -> Unit,
    onClick2: () -> Unit
) {
    val color = when (screen) {
        NavigationScreen.A -> MaterialTheme.colorScheme.primaryContainer
        NavigationScreen.B -> MaterialTheme.colorScheme.secondaryContainer
        NavigationScreen.C -> MaterialTheme.colorScheme.tertiaryContainer
    }

    val (screen1, screen2) = when (screen) {
        NavigationScreen.A -> NavigationScreen.B to NavigationScreen.C
        NavigationScreen.B -> NavigationScreen.A to NavigationScreen.C
        NavigationScreen.C -> NavigationScreen.A to NavigationScreen.B
    }

    Surface(
        color = color
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
            modifier = Modifier.fillMaxSize()
        ) {
            Text("Screen ${screen.name}")

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = onClick1) {
                    Text("Go to ${screen1.name}")
                }

                Button(onClick = onClick2) {
                    Text("Go to ${screen2.name}")
                }
            }
        }
    }
}

private val nav_transitions_1 = LessonPage (
    headingResId = R.string.nav_transitions_1_heading
) {

    ResText(R.string.nav_transitions_1_1)

    CodeListing(
        code = """
            c-NavHost(
                navController = navController,
                enterTransition = { 
                    slideInHorizontally() + fadeIn()
                },
                exitTransition = {
                    slideOutVertically() + fadeOut()
                }
            ) {
                // nav screens
            }
        """.trimIndent()
    )

    val navController = rememberNavController()

    Preview(
        modifier = Modifier
            .align(alignment = Alignment.CenterHorizontally)
            .fillMaxWidth()
            .height(200.dp)
    ) {
        NavHost(
            navController = navController,
            startDestination = ScreenA,
            enterTransition = {
                slideInHorizontally() + fadeIn()
            },
            exitTransition = {
                slideOutVertically() + fadeOut()
            }
        ) {
            composable<ScreenA> {
                NavScreen(
                    screen = NavigationScreen.A,
                    onClick1 = { navController.navigate(ScreenB) },
                    onClick2 = { navController.navigate(ScreenC) }
                )
            }
            composable<ScreenB> {
                NavScreen(
                    screen = NavigationScreen.B,
                    onClick1 = { navController.navigate(ScreenA) },
                    onClick2 = { navController.navigate(ScreenC) }
                )
            }
            composable<ScreenC> {
                NavScreen(
                    screen = NavigationScreen.C,
                    onClick1 = { navController.navigate(ScreenA) },
                    onClick2 = { navController.navigate(ScreenB) }
                )
            }
        }
    }
}

private val nav_transitions_2 = LessonPage (
    headingResId = R.string.nav_transitions_2_heading
) {

    ResText(R.string.nav_transitions_2_1)

    CodeListing(
        code = """
            c-NavHost(
                navController = navController,
                enterTransition = { slideInHorizontally() },
                exitTransition = { slideOutHorizontally() },
                popEnterTransition = { scaleIn() },
                popExitTransition = { scaleOut() }
            ) {
                // Nav screens
            }
        """.trimIndent()
    )

    val navController = rememberNavController()

    Button(
        onClick = {
            navController.navigateUp()
        },
        modifier = Modifier.align(Alignment.CenterHorizontally)
    ) {
        ResText(R.string.nav_transitions_2_nav_back)
    }

    Preview(
        modifier = Modifier
            .align(alignment = Alignment.CenterHorizontally)
            .fillMaxWidth()
            .height(200.dp)
    ) {
        NavHost(
            navController = navController,
            startDestination = ScreenA,
            enterTransition = { slideInHorizontally() },
            exitTransition = { slideOutHorizontally() },
            popEnterTransition = { scaleIn() },
            popExitTransition = { scaleOut() }
        ) {
            composable<ScreenA> {
                NavScreen(
                    screen = NavigationScreen.A,
                    onClick1 = { navController.navigate(ScreenB) },
                    onClick2 = { navController.navigate(ScreenC) }
                )
            }
            composable<ScreenB> {
                NavScreen(
                    screen = NavigationScreen.B,
                    onClick1 = { navController.navigate(ScreenA) },
                    onClick2 = { navController.navigate(ScreenC) }
                )
            }
            composable<ScreenC> {
                NavScreen(
                    screen = NavigationScreen.C,
                    onClick1 = { navController.navigate(ScreenA) },
                    onClick2 = { navController.navigate(ScreenB) }
                )
            }
        }
    }
}

private val nav_transitions_3 = LessonPage (
    headingResId = R.string.nav_transitions_3_heading
) {

    ResText(R.string.nav_transitions_3_1)

    CodeListing(
        code = """
            c-NavHost(
                navController = navController
            ) {
                composable<ScreenA>(
                    enterTransition = { ... },
                    exitTransition = { ... }
                ) {
                    // Screen content
                }
            }
        """.trimIndent()
    )

    val navController = rememberNavController()

    Preview(
        modifier = Modifier
            .align(alignment = Alignment.CenterHorizontally)
            .fillMaxWidth()
            .height(200.dp)
    ) {
        NavHost(
            navController = navController,
            startDestination = ScreenA
        ) {
            composable<ScreenA>(
                enterTransition = { slideInVertically() },
                exitTransition = { slideOutVertically() }
            ) {
                NavScreen(
                    screen = NavigationScreen.A,
                    onClick1 = { navController.navigate(ScreenB) },
                    onClick2 = { navController.navigate(ScreenC) }
                )
            }
            composable<ScreenB>(
                enterTransition = { fadeIn() },
                exitTransition = { fadeOut() }
            ) {
                NavScreen(
                    screen = NavigationScreen.B,
                    onClick1 = { navController.navigate(ScreenA) },
                    onClick2 = { navController.navigate(ScreenC) }
                )
            }
            composable<ScreenC>(
                enterTransition = { scaleIn() },
                exitTransition = { scaleOut() }
            ) {
                NavScreen(
                    screen = NavigationScreen.C,
                    onClick1 = { navController.navigate(ScreenA) },
                    onClick2 = { navController.navigate(ScreenB) }
                )
            }
        }
    }
}

private val nav_transitions_4 = LessonPage (
   headingResId = R.string.nav_transitions_4_heading
) {

    ResText(R.string.nav_transitions_4_1)

    CodeListing(
        code = """
            inline fun <reified T : Any> NavGraphBuilder.animatedComposable(
                noinline content: @Composable () -> Unit
            ) {
                composable<T>(
                    enterTransition = { ...},
                    exitTransition = { ... },
                    ...
                ) { 
                    content()
                }
            } 
        """.trimIndent()
    )

    CodeListing(
        code = """
            c-NavHost(
                navController = navController
            ) {
                animatedComposable<HomeScreen> {
                    // Home screen content
                }
            }
        """.trimIndent()
    )
}

val navigationTransitionsPages = listOf(
    nav_transitions_1,
    nav_transitions_2,
    nav_transitions_3,
    nav_transitions_4
)