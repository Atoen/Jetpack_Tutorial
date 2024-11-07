package com.abachta.jetpacktutorial.courses.layout.quizzes

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Quiz
import com.abachta.jetpacktutorial.data.QuizAnswer
import com.abachta.jetpacktutorial.data.QuizQuestion

val bottomSheetQuiz = Quiz(
    titleResId = R.string.lesson_bottom_sheet_title,
    questions = listOf(
        QuizQuestion(
            textResId = R.string.quiz_bottom_sheet_1,
            answers = listOf(
                QuizAnswer(R.string.quiz_bottom_sheet_1_1, isCorrect = true),
                QuizAnswer(R.string.quiz_bottom_sheet_1_2),
                QuizAnswer(R.string.quiz_bottom_sheet_1_3, isCorrect = true),
                QuizAnswer(R.string.quiz_bottom_sheet_1_4),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_bottom_sheet_2,
            answers = listOf(
                QuizAnswer(R.string.quiz_bottom_sheet_2_1),
                QuizAnswer(R.string.quiz_bottom_sheet_2_2, isCorrect = true),
                QuizAnswer(R.string.quiz_bottom_sheet_2_3),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_bottom_sheet_3,
            answers = listOf(
                QuizAnswer(R.string.quiz_bottom_sheet_3_1),
                QuizAnswer(R.string.quiz_bottom_sheet_3_2, isCorrect = true),
                QuizAnswer(R.string.quiz_bottom_sheet_3_3),
            )
        ),
    )
)