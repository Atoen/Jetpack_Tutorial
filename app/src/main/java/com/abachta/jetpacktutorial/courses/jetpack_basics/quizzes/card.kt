package com.abachta.jetpacktutorial.courses.jetpack_basics.quizzes

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Quiz
import com.abachta.jetpacktutorial.data.QuizAnswer
import com.abachta.jetpacktutorial.data.QuizQuestion

val cardQuiz = Quiz(
    titleResId = R.string.lesson_card_title,
    questions = listOf(
        QuizQuestion(
            textResId = R.string.quiz_card_1,
            answers = listOf(
                QuizAnswer(R.string.quiz_card_1_1),
                QuizAnswer(R.string.quiz_card_1_2, isCorrect = true),
                QuizAnswer(R.string.quiz_card_1_3),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_card_2,
            answers = listOf(
                QuizAnswer(R.string.quiz_card_2_1),
                QuizAnswer(R.string.quiz_card_2_2),
                QuizAnswer(R.string.quiz_card_2_3, isCorrect = true),
            )
        ),
    )
)