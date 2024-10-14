package com.abachta.jetpacktutorial.courses.layout

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Lesson

private val columnAndRow = Lesson(
    titleResId = R.string.lesson_column_row_title,
    descriptionResId = R.string.TODO,
    pages = columnAndRowPages
)

private val box = Lesson(
    titleResId = R.string.lesson_box_title,
    descriptionResId = R.string.TODO,
    pages = boxPages
)

private val grid = Lesson(
    titleResId = R.string.lesson_grid_title,
    descriptionResId = R.string.TODO,
    pages = gridPages
)

private val scaffold = Lesson(
    titleResId = R.string.lesson_scaffold_title,
    descriptionResId = R.string.TODO,
    pages = scaffoldPages
)

val layoutLessons = listOf(
    columnAndRow,
    box,
    grid,
    scaffold
)