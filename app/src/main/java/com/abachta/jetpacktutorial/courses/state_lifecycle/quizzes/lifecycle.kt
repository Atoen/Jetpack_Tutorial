package com.abachta.jetpacktutorial.courses.state_lifecycle.quizzes

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Quiz
import com.abachta.jetpacktutorial.data.QuizAnswer
import com.abachta.jetpacktutorial.data.QuizQuestion

val lifecycleQuiz = Quiz(
    titleResId = R.string.lesson_lifecycle_title,
    questions = listOf(
        QuizQuestion(
            textResId = R.string.quiz_lifecycle_1,
            answers = listOf(
                QuizAnswer(R.string.quiz_lifecycle_1_1),
                QuizAnswer(R.string.quiz_lifecycle_1_2, isCorrect = true),
                QuizAnswer(R.string.quiz_lifecycle_1_3),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_lifecycle_2,
            answers = listOf(
                QuizAnswer(R.string.quiz_lifecycle_2_1),
                QuizAnswer(R.string.quiz_lifecycle_2_2),
                QuizAnswer(R.string.quiz_lifecycle_2_3, isCorrect = true),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_lifecycle_3,
            answers = listOf(
                QuizAnswer(R.string.quiz_lifecycle_3_1),
                QuizAnswer(R.string.quiz_lifecycle_3_2, isCorrect = true),
                QuizAnswer(R.string.quiz_lifecycle_3_3),
            )
        ),
    )
)