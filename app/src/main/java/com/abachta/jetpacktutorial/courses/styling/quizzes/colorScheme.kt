package com.abachta.jetpacktutorial.courses.styling.quizzes

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Quiz
import com.abachta.jetpacktutorial.data.QuizAnswer
import com.abachta.jetpacktutorial.data.QuizQuestion

val colorSchemeQuiz = Quiz(
    titleResId = R.string.lesson_color_scheme_title,
    questions = listOf(
        QuizQuestion(
            textResId = R.string.quiz_color_scheme_1,
            answers = listOf(
                QuizAnswer(R.string.quiz_color_scheme_1_1),
                QuizAnswer(R.string.quiz_color_scheme_1_2, isCorrect = true),
                QuizAnswer(R.string.quiz_color_scheme_1_3),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_color_scheme_2,
            answers = listOf(
                QuizAnswer(R.string.quiz_color_scheme_2_1),
                QuizAnswer(R.string.quiz_color_scheme_2_2, isCorrect = true),
                QuizAnswer(R.string.quiz_color_scheme_2_3),
            )
        ),
    )
)