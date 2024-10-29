package com.abachta.jetpacktutorial.courses.navigation.quizzes

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Quiz
import com.abachta.jetpacktutorial.data.QuizAnswer
import com.abachta.jetpacktutorial.data.QuizQuestion

val backHandlerQuiz = Quiz(
    titleResId = R.string.lesson_back_handler_title,
    questions = listOf(
        QuizQuestion(
            textResId = R.string.quiz_back_1,
            answers = listOf(
                QuizAnswer(R.string.quiz_back_1_1),
                QuizAnswer(R.string.quiz_back_1_2, isCorrect = true),
                QuizAnswer(R.string.quiz_back_1_3),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_back_2,
            answers = listOf(
                QuizAnswer(R.string.quiz_back_2_1),
                QuizAnswer(R.string.quiz_back_2_2),
                QuizAnswer(R.string.quiz_back_2_3, isCorrect = true),
            )
        )
    )
)