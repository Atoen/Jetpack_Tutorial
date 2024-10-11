package com.abachta.jetpacktutorial.courses.jetpack_basics.quizzes

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Quiz
import com.abachta.jetpacktutorial.data.QuizAnswer
import com.abachta.jetpacktutorial.data.QuizQuestion

val textQuiz = Quiz(
    titleResId = R.string.lesson_text_title,
    questions = listOf(
        QuizQuestion(
            textResId = R.string.quiz_text_1,
            answers = listOf(
                QuizAnswer(R.string.quiz_text_1_1, isCorrect = true),
                QuizAnswer(R.string.quiz_text_1_2),
                QuizAnswer(R.string.quiz_text_1_3)
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_text_2,
            answers = listOf(
                QuizAnswer(R.string.quiz_text_2_1),
                QuizAnswer(R.string.quiz_text_2_2, isCorrect = true),
                QuizAnswer(R.string.quiz_text_2_3)
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_text_3,
            answers = listOf(
                QuizAnswer(R.string.quiz_text_3_1),
                QuizAnswer(R.string.quiz_text_3_2),
                QuizAnswer(R.string.quiz_text_3_3, isCorrect = true)
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_text_4,
            answers = listOf(
                QuizAnswer(R.string.quiz_text_4_1),
                QuizAnswer(R.string.quiz_text_4_2),
                QuizAnswer(R.string.quiz_text_4_3, isCorrect = true)
            )
        )
    )
)