package com.abachta.jetpacktutorial.courses.navigation

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Lesson

private val navControllerAndHost = Lesson(
    titleResId = R.string.lesson_nav_ctr_host_title,
    descriptionResId = R.string.TODO,
    pages = navControllerPages,
)

private val navigationContainers = Lesson(
    titleResId = R.string.lesson_nav_containers_title,
    descriptionResId = R.string.TODO,
    pages = navigationContainersPages,
)

private val backHandler = Lesson(
    titleResId = R.string.lesson_back_handler_title,
    descriptionResId = R.string.TODO,
    pages = backHandlerPages,
)

private val navigationSuite = Lesson(
    titleResId = R.string.lesson_navigation_suite_title,
    descriptionResId = R.string.TODO,
    pages = listOf(),
)

val navigationLessons = listOf(
    navControllerAndHost,
    navigationContainers,
    backHandler,
    navigationSuite
)