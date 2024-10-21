package com.abachta.jetpacktutorial.courses.styling

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.courses.styling.quizzes.appThemeQuiz
import com.abachta.jetpacktutorial.courses.styling.quizzes.colorSchemeQuiz
import com.abachta.jetpacktutorial.courses.styling.quizzes.materialThemeQuiz
import com.abachta.jetpacktutorial.data.Lesson

private val colorScheme = Lesson(
    titleResId = R.string.lesson_color_scheme_title,
    descriptionResId = R.string.TODO,
    pages = colorSchemePages,
    quiz = colorSchemeQuiz
)

private val materialTheme = Lesson(
    titleResId = R.string.lesson_material_theme_title,
    descriptionResId = R.string.TODO,
    pages = materialThemePages,
    quiz = materialThemeQuiz
)

private val appTheme = Lesson(
    titleResId = R.string.lesson_app_theme,
    descriptionResId = R.string.TODO,
    pages = appThemePages,
    quiz = appThemeQuiz
)

val styingLessons = listOf(
    colorScheme,
    materialTheme,
    appTheme
)