package com.abachta.jetpacktutorial.courses.layout.quizzes

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Quiz
import com.abachta.jetpacktutorial.data.QuizAnswer
import com.abachta.jetpacktutorial.data.QuizQuestion

val gridQuiz = Quiz(
    titleResId = R.string.lesson_grid_title,
    questions = listOf(
        QuizQuestion(
            textResId = R.string.quiz_grid_1,
            answers = listOf(
                QuizAnswer(R.string.quiz_grid_1_1),
                QuizAnswer(R.string.quiz_grid_1_2, isCorrect = true),
                QuizAnswer(R.string.quiz_grid_1_3),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_grid_2,
            answers = listOf(
                QuizAnswer(R.string.quiz_grid_2_1),
                QuizAnswer(R.string.quiz_grid_2_2),
                QuizAnswer(R.string.quiz_grid_2_3, isCorrect = true),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_grid_3,
            answers = listOf(
                QuizAnswer(R.string.quiz_grid_3_1),
                QuizAnswer(R.string.quiz_grid_3_2, isCorrect = true),
                QuizAnswer(R.string.quiz_grid_3_3),
            )
        ),
    )
)