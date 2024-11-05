package com.abachta.jetpacktutorial.courses.advanced.quizzes

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Quiz
import com.abachta.jetpacktutorial.data.QuizAnswer
import com.abachta.jetpacktutorial.data.QuizQuestion

val permissionsQuiz = Quiz(
    titleResId = R.string.lesson_permissions_title,
    questions = listOf(
        QuizQuestion(
            textResId = R.string.quiz_permissions_1,
            answers = listOf(
                QuizAnswer(R.string.quiz_permissions_1_1),
                QuizAnswer(R.string.quiz_permissions_1_2, isCorrect = true),
                QuizAnswer(R.string.quiz_permissions_1_3),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_permissions_2,
            answers = listOf(
                QuizAnswer(R.string.quiz_permissions_2_1),
                QuizAnswer(R.string.quiz_permissions_2_2),
                QuizAnswer(R.string.quiz_permissions_2_3, isCorrect = true),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_permissions_3,
            answers = listOf(
                QuizAnswer(R.string.quiz_permissions_3_1),
                QuizAnswer(R.string.quiz_permissions_3_2, isCorrect = true),
                QuizAnswer(R.string.quiz_permissions_3_3),
            )
        ),
    )
)