package com.abachta.jetpacktutorial.courses.state_lifecycle

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Lesson

private val lifecycle = Lesson(
    titleResId = R.string.lesson_lifecycle_title,
    descriptionResId = R.string.TODO,
    pages = lifecyclePages
)

private val rememberingState = Lesson(
    titleResId = R.string.lesson_remember_title,
    descriptionResId = R.string.TODO,
    pages = rememberPages
)

private val viewModel = Lesson(
    titleResId = R.string.lesson_view_model_title,
    descriptionResId = R.string.TODO,
    pages = listOf()
)

private val persistent = Lesson(
    titleResId = R.string.lesson_persistent_title,
    descriptionResId = R.string.TODO,
    pages = listOf()
)

val stateLessons = listOf(
    lifecycle,
    rememberingState,
    viewModel,
    persistent
)