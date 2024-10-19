package com.abachta.jetpacktutorial.courses.styling

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Lesson

private val colorScheme = Lesson(
    titleResId = R.string.lesson_color_scheme_title,
    descriptionResId = R.string.TODO,
    pages = colorSchemePages
)

private val materialTheme = Lesson(
    titleResId = R.string.lesson_material_theme_title,
    descriptionResId = R.string.TODO,
    pages = materialThemePages
)

private val appTheme = Lesson(
    titleResId = R.string.lesson_app_theme,
    descriptionResId = R.string.TODO,
    pages = appThemePages
)

val styingLessons = listOf(
    colorScheme,
    materialTheme,
    appTheme
)