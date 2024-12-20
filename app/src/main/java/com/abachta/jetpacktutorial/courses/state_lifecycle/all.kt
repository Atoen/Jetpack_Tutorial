package com.abachta.jetpacktutorial.courses.state_lifecycle

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.courses.state_lifecycle.quizzes.lifecycleQuiz
import com.abachta.jetpacktutorial.courses.state_lifecycle.quizzes.persistentQuiz
import com.abachta.jetpacktutorial.courses.state_lifecycle.quizzes.rememberQuiz
import com.abachta.jetpacktutorial.courses.state_lifecycle.quizzes.sideEffectQuiz
import com.abachta.jetpacktutorial.courses.state_lifecycle.quizzes.viewModelQuiz
import com.abachta.jetpacktutorial.data.Lesson

private val lifecycle = Lesson(
    titleResId = R.string.lesson_lifecycle_title,
    descriptionResId = R.string.TODO,
    pages = lifecyclePages,
    quiz = lifecycleQuiz
)

private val rememberingState = Lesson(
    titleResId = R.string.lesson_remember_title,
    descriptionResId = R.string.TODO,
    pages = rememberPages,
    quiz = rememberQuiz
)

private val sideEffects = Lesson(
    titleResId = R.string.lesson_side_effects_title,
    descriptionResId = R.string.TODO,
    pages = sideEffectPages,
    quiz = sideEffectQuiz
)

private val viewModel = Lesson(
    titleResId = R.string.lesson_view_model_title,
    descriptionResId = R.string.TODO,
    pages = viewModelPages,
    quiz = viewModelQuiz
)

private val persistent = Lesson(
    titleResId = R.string.lesson_persistent_title,
    descriptionResId = R.string.TODO,
    pages = persistentPages,
    quiz = persistentQuiz
)

val stateLessons = listOf(
    lifecycle,
    rememberingState,
    sideEffects,
    viewModel,
    persistent
)