package com.abachta.jetpacktutorial.courses.jetpack_basics.quizzes

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Quiz
import com.abachta.jetpacktutorial.data.QuizAnswer
import com.abachta.jetpacktutorial.data.QuizQuestion

val imageQuiz = Quiz(
    titleResId = R.string.lesson_image_title,
    questions = listOf(
        QuizQuestion(
            textResId = R.string.quiz_image_1,
            answers = listOf(
                QuizAnswer(R.string.quiz_image_1_1),
                QuizAnswer(R.string.quiz_image_1_2, isCorrect = true),
                QuizAnswer(R.string.quiz_image_1_3),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_image_2,
            answers = listOf(
                QuizAnswer(R.string.quiz_image_2_1),
                QuizAnswer(R.string.quiz_image_2_2),
                QuizAnswer(R.string.quiz_image_2_3, isCorrect = true),
            )
        ),
    )
)