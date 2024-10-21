package com.abachta.jetpacktutorial.courses.styling.quizzes

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Quiz
import com.abachta.jetpacktutorial.data.QuizAnswer
import com.abachta.jetpacktutorial.data.QuizQuestion

val materialThemeQuiz = Quiz(
    titleResId = R.string.lesson_material_theme_title,
    questions = listOf(
        QuizQuestion(
            textResId = R.string.quiz_material_theme_1,
            answers = listOf(
                QuizAnswer(R.string.quiz_material_theme_1_1),
                QuizAnswer(R.string.quiz_material_theme_1_2, isCorrect = true),
                QuizAnswer(R.string.quiz_material_theme_1_3),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_material_theme_2,
            answers = listOf(
                QuizAnswer(R.string.quiz_material_theme_2_1),
                QuizAnswer(R.string.quiz_material_theme_2_2),
                QuizAnswer(R.string.quiz_material_theme_2_3, isCorrect = true),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_material_theme_3,
            answers = listOf(
                QuizAnswer(R.string.quiz_material_theme_3_1),
                QuizAnswer(R.string.quiz_material_theme_3_2),
                QuizAnswer(R.string.quiz_material_theme_3_3, isCorrect = true),
            )
        ),
    )
)