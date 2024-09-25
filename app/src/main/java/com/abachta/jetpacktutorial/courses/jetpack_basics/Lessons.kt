package com.abachta.jetpacktutorial.courses.jetpack_basics

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Lesson

val Text = Lesson(
    titleResId = R.string.lesson_text_title,
    descriptionResId = R.string.TODO,
    pages = textPages
)

val jetpackBasicsLessons = listOf(
    Text
)