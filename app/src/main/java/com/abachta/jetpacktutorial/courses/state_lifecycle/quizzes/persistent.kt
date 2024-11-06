package com.abachta.jetpacktutorial.courses.state_lifecycle.quizzes

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Quiz
import com.abachta.jetpacktutorial.data.QuizAnswer
import com.abachta.jetpacktutorial.data.QuizQuestion

val persistentQuiz = Quiz(
    titleResId = R.string.lesson_persistent_title,
    questions = listOf(
        QuizQuestion(
            textResId = R.string.quiz_persistent_1,
            answers = listOf(
                QuizAnswer(R.string.quiz_persistent_1_1),
                QuizAnswer(R.string.quiz_persistent_1_2),
                QuizAnswer(R.string.quiz_persistent_1_3, isCorrect = true),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_persistent_2,
            answers = listOf(
                QuizAnswer(R.string.quiz_persistent_2_1),
                QuizAnswer(R.string.quiz_persistent_2_2, isCorrect = true),
                QuizAnswer(R.string.quiz_persistent_2_3),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_persistent_3,
            answers = listOf(
                QuizAnswer(R.string.quiz_persistent_3_1),
                QuizAnswer(R.string.quiz_persistent_3_2, isCorrect = true),
                QuizAnswer(R.string.quiz_persistent_3_3),
            )
        ),
    )
)