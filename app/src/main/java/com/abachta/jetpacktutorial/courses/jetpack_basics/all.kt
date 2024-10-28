package com.abachta.jetpacktutorial.courses.jetpack_basics

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.courses.jetpack_basics.challenges.buttonChallenge
import com.abachta.jetpacktutorial.courses.jetpack_basics.challenges.textChallenge
import com.abachta.jetpacktutorial.courses.jetpack_basics.quizzes.buttonQuiz
import com.abachta.jetpacktutorial.courses.jetpack_basics.quizzes.cardQuiz
import com.abachta.jetpacktutorial.courses.jetpack_basics.quizzes.imageQuiz
import com.abachta.jetpacktutorial.courses.jetpack_basics.quizzes.modifierQuiz
import com.abachta.jetpacktutorial.courses.jetpack_basics.quizzes.textFieldQuiz
import com.abachta.jetpacktutorial.courses.jetpack_basics.quizzes.textQuiz
import com.abachta.jetpacktutorial.data.Lesson

private val text = Lesson(
    titleResId = R.string.lesson_text_title,
    descriptionResId = R.string.TODO,
    pages = textPages,
    quiz = textQuiz,
    challenge = textChallenge
)

private val button = Lesson(
    titleResId = R.string.lesson_button_title,
    descriptionResId = R.string.TODO,
    pages = buttonPages,
    quiz = buttonQuiz,
    challenge = buttonChallenge
)

private val textField = Lesson(
    titleResId = R.string.lesson_text_field_title,
    descriptionResId = R.string.TODO,
    pages = textFieldPages,
    quiz = textFieldQuiz
)

private val image = Lesson(
    titleResId = R.string.lesson_image_title,
    descriptionResId = R.string.TODO,
    pages = imagePages,
    quiz = imageQuiz
)

private val card = Lesson(
    titleResId = R.string.lesson_card_title,
    descriptionResId = R.string.TODO,
    pages = cardPages,
    quiz = cardQuiz
)

private val modifier = Lesson(
    titleResId = R.string.lesson_modifier_title,
    descriptionResId = R.string.TODO,
    pages = modifierPages,
    quiz = modifierQuiz
)

private val sideEffects = Lesson(
    titleResId = R.string.lesson_side_effects_title,
    descriptionResId = R.string.TODO,
    pages = sideEffectPages
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
    sideEffects,
    toast
)