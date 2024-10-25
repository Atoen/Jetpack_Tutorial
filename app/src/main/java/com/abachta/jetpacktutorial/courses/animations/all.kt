package com.abachta.jetpacktutorial.courses.animations

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Lesson

private val animatingValues = Lesson(
    titleResId = R.string.lesson_animate_values_title,
    descriptionResId = R.string.TODO,
    pages = animatingValuesPages,
)

private val animatedVisibility = Lesson(
    titleResId = R.string.lesson_animated_visibility_title,
    descriptionResId = R.string.TODO,
    pages = animatedVisibilityPages,
)

private val navigationTransitions = Lesson(
    titleResId = R.string.lesson_nav_transitions_title,
    descriptionResId = R.string.TODO,
    pages = listOf(),
)

val animationsLessons = listOf(
    animatingValues,
    animatedVisibility,
    navigationTransitions
)