package com.abachta.jetpacktutorial.courses.layout.quizzes

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Quiz
import com.abachta.jetpacktutorial.data.QuizAnswer
import com.abachta.jetpacktutorial.data.QuizQuestion

val boxQuiz = Quiz(
    titleResId = R.string.lesson_box_title,
    questions = listOf(
        QuizQuestion(
            textResId = R.string.quiz_box_1,
            answers = listOf(
                QuizAnswer(R.string.quiz_box_1_1, isCorrect = true),
                QuizAnswer(R.string.quiz_box_1_2),
                QuizAnswer(R.string.quiz_box_1_3, isCorrect = true),
                QuizAnswer(R.string.quiz_box_1_4),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_box_2,
            answers = listOf(
                QuizAnswer(R.string.quiz_box_2_1),
                QuizAnswer(R.string.quiz_box_2_2, isCorrect = true),
                QuizAnswer(R.string.quiz_box_2_3),
            )
        ),
    )
)