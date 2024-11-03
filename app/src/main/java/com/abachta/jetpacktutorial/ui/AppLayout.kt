package com.abachta.jetpacktutorial.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.courses.getQuizById
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

    val screenData = currentBackStackEntry.getScreenData()

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
                title = { Text(stringResource(screenData.title)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        if (screenData.isHome) {
                            navController.navigate(Screen.Settings)
                        } else {
                            navController.navigateUp()
                        }
                    }) {
                        val icon = if (screenData.isHome) {
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
            modifier = Modifier.padding(contentPadding),
            enterTransition = {
                val initialData = initialState.getScreenData()
                val targetData = targetState.getScreenData()
                val delta = initialData.order - targetData.order

                if (delta >= 0) {
                    slideInFromLeft
                } else {
                    slideInFromRight
                }
            },
            exitTransition = {
                val initialData = initialState.getScreenData()
                val targetData = targetState.getScreenData()
                val delta = initialData.order - targetData.order

                if (delta >= 0) {
                    slideOutToRight
                } else {
                    slideOutToLeft
                }
            }
        ) {
            composable<Screen.Home> {
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

            composable<Screen.Settings> {
                SettingsScreen(
                    viewModel = settingsViewModel,
                    onClearLessons = courseViewModel::clearProgress,
                    onChangePopupSettings = {
                        courseViewModel.shouldAnimatePopup = it.enabled
                    }
                )
            }

            composable<Screen.Course> {
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

            composable<Screen.Lesson> {
                val arg = it.toRoute<Screen.Lesson>()
                LessonScreen(
                    lessonData = arg,
                    onBack = navController::navigateUp,
                    onLessonComplete = courseViewModel::markLessonCompleted,
                    onGoToQuiz = navController::navigateToQuiz,
                    onGoToCodeChallenge = navController::navigateToChallenge,
                    visualsAccessor = settingsViewModel.featureAccessor
                )
            }

            composable<Screen.Quiz> {
                val arg = it.toRoute<Screen.Quiz>()
                val quiz = getQuizById(arg.id)

                // Reset the quiz on first composition after navigation
                // and save the state to prevent resetting on configuration changes
                var reset by rememberSaveable(quiz.id) {
                    mutableStateOf(true)
                }

                LaunchedEffect(quiz.id) {
                    reset = false
                }

                QuizScreen(
                    quiz = courseViewModel.getQuizModel(quiz, reset),
                    shuffleMode = settingsViewModel.questionShuffling,
                    onQuizFinished = navController::navigateUp
                )
            }

            composable<Screen.Challenge> {
                val arg = it.toRoute<Screen.Challenge>()
                ChallengeScreen(
                    challengeData = arg
                )
            }

            composable<Screen.DeepLinkScreen>(
                deepLinks = listOf(
                    navDeepLink<Screen.DeepLinkScreen>(
                        basePath = "custom-scheme://deeplink-host"
                    )
                )
            ) {
                val number = it.toRoute<Screen.DeepLinkScreen>().number
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "The number is $number")
                }
            }
        }
    }
}