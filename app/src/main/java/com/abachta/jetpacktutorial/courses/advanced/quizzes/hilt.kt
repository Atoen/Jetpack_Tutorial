package com.abachta.jetpacktutorial.courses.advanced.quizzes

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Quiz
import com.abachta.jetpacktutorial.data.QuizAnswer
import com.abachta.jetpacktutorial.data.QuizQuestion

val hiltQuiz = Quiz(
    titleResId = R.string.lesson_hilt_title,
    questions = listOf(
        QuizQuestion(
            textResId = R.string.quiz_hilt_1,
            answers = listOf(
                QuizAnswer(R.string.quiz_hilt_1_1),
                QuizAnswer(R.string.quiz_hilt_1_2),
                QuizAnswer(R.string.quiz_hilt_1_3, isCorrect = true),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_hilt_2,
            answers = listOf(
                QuizAnswer(R.string.quiz_hilt_2_1),
                QuizAnswer(R.string.quiz_hilt_2_2),
                QuizAnswer(R.string.quiz_hilt_2_3, isCorrect = true),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_hilt_3,
            answers = listOf(
                QuizAnswer(R.string.quiz_hilt_3_1),
                QuizAnswer(R.string.quiz_hilt_3_2, isCorrect = true),
                QuizAnswer(R.string.quiz_hilt_3_3),
            )
        ),
    )
)