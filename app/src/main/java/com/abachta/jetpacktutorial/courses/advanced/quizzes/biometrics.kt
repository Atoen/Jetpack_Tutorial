package com.abachta.jetpacktutorial.courses.advanced.quizzes

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Quiz
import com.abachta.jetpacktutorial.data.QuizAnswer
import com.abachta.jetpacktutorial.data.QuizQuestion

val biometricsQuiz = Quiz(
    titleResId = R.string.lesson_biometrics_title,
    questions = listOf(
        QuizQuestion(
            textResId = R.string.quiz_biometrics_1,
            answers = listOf(
                QuizAnswer(R.string.quiz_biometrics_1_1),
                QuizAnswer(R.string.quiz_biometrics_1_2),
                QuizAnswer(R.string.quiz_biometrics_1_3, isCorrect = true),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_biometrics_2,
            answers = listOf(
                QuizAnswer(R.string.quiz_biometrics_2_1),
                QuizAnswer(R.string.quiz_biometrics_2_2, isCorrect = true),
                QuizAnswer(R.string.quiz_biometrics_2_3),
            )
        )
    )
)