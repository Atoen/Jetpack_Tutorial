package com.abachta.jetpacktutorial.courses.navigation

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.courses.navigation.quizzes.backHandlerQuiz
import com.abachta.jetpacktutorial.courses.navigation.quizzes.navControllerAndHostQuiz
import com.abachta.jetpacktutorial.courses.navigation.quizzes.navigationContainerQuiz
import com.abachta.jetpacktutorial.data.Lesson

private val navControllerAndHost = Lesson(
    titleResId = R.string.lesson_nav_ctr_host_title,
    descriptionResId = R.string.TODO,
    pages = navControllerPages,
    quiz = navControllerAndHostQuiz
)

private val navigationContainers = Lesson(
    titleResId = R.string.lesson_nav_containers_title,
    descriptionResId = R.string.TODO,
    pages = navigationContainersPages,
    quiz = navigationContainerQuiz
)

private val backHandler = Lesson(
    titleResId = R.string.lesson_back_handler_title,
    descriptionResId = R.string.TODO,
    pages = backHandlerPages,
    quiz = backHandlerQuiz
)

val navigationLessons = listOf(
    navControllerAndHost,
    navigationContainers,
    backHandler
)