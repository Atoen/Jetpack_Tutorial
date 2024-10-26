package com.abachta.jetpacktutorial.courses.layout

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.courses.layout.quizzes.boxQuiz
import com.abachta.jetpacktutorial.courses.layout.quizzes.columnAndRowQuiz
import com.abachta.jetpacktutorial.courses.layout.quizzes.gridQuiz
import com.abachta.jetpacktutorial.courses.layout.quizzes.scaffoldQuiz
import com.abachta.jetpacktutorial.data.Lesson

private val columnAndRow = Lesson(
    titleResId = R.string.lesson_column_row_title,
    descriptionResId = R.string.TODO,
    pages = columnAndRowPages,
    quiz = columnAndRowQuiz
)

private val box = Lesson(
    titleResId = R.string.lesson_box_title,
    descriptionResId = R.string.TODO,
    pages = boxPages,
    quiz = boxQuiz
)

private val grid = Lesson(
    titleResId = R.string.lesson_grid_title,
    descriptionResId = R.string.TODO,
    pages = gridPages,
    quiz = gridQuiz
)

private val scaffold = Lesson(
    titleResId = R.string.lesson_scaffold_title,
    descriptionResId = R.string.TODO,
    pages = scaffoldPages,
    quiz = scaffoldQuiz
)

private val bottomSheet = Lesson(
    titleResId = R.string.lesson_bottom_sheet_title,
    descriptionResId = R.string.TODO,
    pages = bottomSheetPages
)

private val pager = Lesson(
    titleResId = R.string.lesson_pager_title,
    descriptionResId = R.string.TODO,
    pages = pagerPages,
)

val layoutLessons = listOf(
    columnAndRow,
    box,
    grid,
    scaffold,
    bottomSheet,
    pager
)