package com.abachta.jetpacktutorial.courses.jetpack_basics

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.courses.jetpack_basics.quizzes.textQuiz
import com.abachta.jetpacktutorial.data.Lesson

private val text = Lesson(
    titleResId = R.string.lesson_text_title,
    descriptionResId = R.string.TODO,
    pages = textPages,
    quiz = textQuiz
)

private val button = Lesson(
    titleResId = R.string.lesson_button_title,
    descriptionResId = R.string.TODO,
    pages = buttonPages
)

private val textField = Lesson(
    titleResId = R.string.lesson_text_field_title,
    descriptionResId = R.string.TODO,
    pages = textFieldPages
)

private val image = Lesson(
    titleResId = R.string.lesson_image_title,
    descriptionResId = R.string.TODO,
    pages = imagePages
)

private val card = Lesson(
    titleResId = R.string.lesson_card_title,
    descriptionResId = R.string.TODO,
    pages = cardPages
)

private val modifier = Lesson(
    titleResId = R.string.lesson_modifier_title,
    descriptionResId = R.string.TODO,
    pages = modifierPages
)

private val toast = Lesson(
    titleResId = R.string.lesson_toast_title,
    descriptionResId = R.string.TODO,
    pages = toastPages
)

val jetpackBasicsLessons = listOf(
    text,
    button,
    textField,
    image,
    card,
    modifier,
    toast
)

val jetpackBasicsQuizzes = listOf(
    textQuiz
)