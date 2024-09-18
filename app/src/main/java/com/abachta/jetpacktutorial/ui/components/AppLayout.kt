package com.abachta.jetpacktutorial.ui.components

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.core.os.LocaleListCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.courses
import com.abachta.jetpacktutorial.lessons.gettingStartedLessons
import com.abachta.jetpacktutorial.ui.AppLocale
import com.abachta.jetpacktutorial.ui.SnackbarController
import com.abachta.jetpacktutorial.ui.SnackbarEvent
import com.abachta.jetpacktutorial.ui.appBarTitle
import com.abachta.jetpacktutorial.ui.navigateToCourse
import com.abachta.jetpacktutorial.ui.navigateToLesson
import com.abachta.jetpacktutorial.ui.screens.CourseScreen
import com.abachta.jetpacktutorial.ui.screens.HomeScreen
import com.abachta.jetpacktutorial.ui.screens.LessonScreen
import com.abachta.jetpacktutorial.ui.screens.Screen
import com.abachta.jetpacktutorial.ui.screens.SettingsScreen
import com.abachta.jetpacktutorial.ui.slidingComposable
import com.abachta.jetpacktutorial.viewmodels.SettingsViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppLayout(
    viewModel: SettingsViewModel,
    onExit: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val titleRes = currentBackStackEntry.appBarTitle()
    val isOnHomeScreen = titleRes == R.string.app_name

    val localeTag = AppCompatDelegate.getApplicationLocales().get(0)?.language ?: "en"
    val locale = AppLocale.fromLanguageTag(localeTag)

    ObserveAsEvents(
        flow = SnackbarController.events,
        key1 = snackbarHostState
    ) { event ->
        scope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()
            val result = snackbarHostState.showSnackbar(
                message = event.message,
                duration = event.duration,
                actionLabel = event.action?.name
            )

            if (result == SnackbarResult.ActionPerformed) {
                event.action?.action?.invoke()
            }
        }
    }

    val backAgainMessage = stringResource(R.string.back_again_to_close)
    TwiceBackHandler(
        onFirstBack = {
            scope.launch {
                SnackbarController.sendEvent(
                    SnackbarEvent(
                        message = backAgainMessage,
                        SnackbarDuration.Short
                    )
                )
            }
        },
        onSecondBack = onExit
    )

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(stringResource(titleRes))
                },
                navigationIcon = {
                    IconButton(onClick = {
                        if (isOnHomeScreen) {
                            navController.navigate(Screen.Settings)
                        } else {
                            navController.navigateUp()
                        }
                    }) {
                        if (isOnHomeScreen) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Menu"
                            )
                        } else {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                }
            )
        }
    ) { contentPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home,
            modifier = Modifier.padding(contentPadding),
        ) {
            slidingComposable<Screen.Home> {
                HomeScreen(
                    courses = courses,
                    lessonToContinue = gettingStartedLessons.first(),
                    onCourseClick = { navController.navigateToCourse(it) },
                    onContinueClick = { course, lesson ->
                        navController.navigateToCourse(course)
                        navController.navigateToLesson(lesson, course.id)
                    }
                )
            }

            slidingComposable<Screen.Settings> {
                SettingsScreen(
                    appTheme = viewModel.theme,
                    onThemeSelected = { viewModel.theme = it },
                    appLocale = locale,
                    onLocaleSelected = { locale ->
                        AppCompatDelegate.setApplicationLocales(
                            LocaleListCompat.forLanguageTags(
                                locale.tag
                            )
                        )
                    }
                )
            }

            slidingComposable<Screen.Course> {
                val arg = it.toRoute<Screen.Course>()
                CourseScreen(
                    courseData = arg,
                    onLessonClick = { lesson ->
                        navController.navigateToLesson(lesson, arg.id)
                    }
                )
            }

            slidingComposable<Screen.Lesson> {
                val arg = it.toRoute<Screen.Lesson>()
                LessonScreen(
                    lessonData = arg,
                    onLessonCompleted = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}