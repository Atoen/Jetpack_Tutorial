package com.abachta.jetpacktutorial.courses.layout.quizzes

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Quiz
import com.abachta.jetpacktutorial.data.QuizAnswer
import com.abachta.jetpacktutorial.data.QuizQuestion

val columnAndRowQuiz = Quiz(
    titleResId = R.string.lesson_column_row_title,
    questions = listOf(
        QuizQuestion(
            textResId = R.string.quiz_column_row_1,
            answers = listOf(
                QuizAnswer(R.string.quiz_column_row_1_1, isCorrect = true),
                QuizAnswer(R.string.quiz_column_row_1_2),
                QuizAnswer(R.string.quiz_column_row_1_3),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_column_row_2,
            answers = listOf(
                QuizAnswer(R.string.quiz_column_row_2_1),
                QuizAnswer(R.string.quiz_column_row_2_2, isCorrect = true),
                QuizAnswer(R.string.quiz_column_row_2_3),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_column_row_3,
            answers = listOf(
                QuizAnswer(R.string.quiz_column_row_3_1),
                QuizAnswer(R.string.quiz_column_row_3_2),
                QuizAnswer(R.string.quiz_column_row_3_3, isCorrect = true),
            )
        ),
    )
)