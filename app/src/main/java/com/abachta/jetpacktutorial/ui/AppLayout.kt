package com.abachta.jetpacktutorial.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.ui.components.ObserveAsEvents
import com.abachta.jetpacktutorial.ui.components.TwiceBackHandler
import com.abachta.jetpacktutorial.ui.screens.ChallengeScreen
import com.abachta.jetpacktutorial.ui.screens.CourseScreen
import com.abachta.jetpacktutorial.ui.screens.HomeScreen
import com.abachta.jetpacktutorial.ui.screens.LessonScreen
import com.abachta.jetpacktutorial.ui.screens.QuizScreen
import com.abachta.jetpacktutorial.ui.screens.SettingsScreen
import com.abachta.jetpacktutorial.viewmodels.CourseViewModel
import com.abachta.jetpacktutorial.viewmodels.SettingsViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppLayout(
    settingsViewModel: SettingsViewModel,
    onExit: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val titleRes = currentBackStackEntry.appBarTitle()
    val isOnHomeScreen = titleRes == R.string.app_name

    ObserveAsEvents(
        flow = SnackbarController.events,
        key1 = snackbarHostState
    ) { event ->
        scope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()
            val result = snackbarHostState.showSnackbar(
                message = event.message,
                duration = event.duration,
                withDismissAction = event.dismissible,
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

    val courseViewModel = hiltViewModel<CourseViewModel>()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(titleRes)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        if (isOnHomeScreen) {
                            navController.navigate(Screen.Settings)
                        } else {
                            navController.navigateUp()
                        }
                    }) {
                        val icon = if (isOnHomeScreen) {
                            Icons.Filled.Menu
                        } else Icons.AutoMirrored.Filled.ArrowBack

                        Icon(
                            imageVector = icon,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { contentPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home,
            modifier = Modifier.padding(contentPadding)
        ) {
            slidingComposable<Screen.Home> {
                HomeScreen(
                    viewModel = courseViewModel,
                    showPopup = settingsViewModel.lessonPopup.enabled,
                    onCourseClick = navController::navigateToCourse,
                    onContinueClick = { course, lesson ->
                        navController.navigateToCourse(course)
                        navController.navigateToLesson(lesson, course.id)
                    },
                )
            }

            slidingComposable<Screen.Settings> {
                SettingsScreen(
                    viewModel = settingsViewModel,
                    onClearLessons = courseViewModel::clearProgress,
                    onChangePopupSettings = {
                        courseViewModel.shouldAnimatePopup = it.enabled
                    }
                )
            }

            slidingComposable<Screen.Course> {
                val arg = it.toRoute<Screen.Course>()
                CourseScreen(
                    courseData = arg,
                    onLessonClick = { lesson ->
                        navController.navigateToLesson(lesson, arg.id)
                    },
                    onGoToQuiz = navController::navigateToQuiz,
                    onGoToChallenge = navController::navigateToChallenge
                )
            }

            slidingComposable<Screen.Lesson> {
                val arg = it.toRoute<Screen.Lesson>()
                LessonScreen(
                    lessonData = arg,
                    onBack = navController::navigateUp,
                    onLessonComplete = courseViewModel::markLessonCompleted,
                    onGoToQuiz = navController::navigateToQuiz,
                    onGoToCodeChallenge = navController::navigateToChallenge
                )
            }

            slidingComposable<Screen.Quiz> {
                val arg = it.toRoute<Screen.Quiz>()
                QuizScreen(
                    quiz = courseViewModel.getQuizModel(arg.id),
                    shuffleMode = settingsViewModel.questionShuffling,
                    onQuizFinished = navController::navigateUp
                )
            }

            slidingComposable<Screen.Challenge> {
                val arg = it.toRoute<Screen.Challenge>()

                ChallengeScreen(
                    challengeData = arg
                )
            }
        }
    }
}