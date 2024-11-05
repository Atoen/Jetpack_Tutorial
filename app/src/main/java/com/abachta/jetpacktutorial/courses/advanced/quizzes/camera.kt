package com.abachta.jetpacktutorial.courses.advanced.quizzes

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Quiz
import com.abachta.jetpacktutorial.data.QuizAnswer
import com.abachta.jetpacktutorial.data.QuizQuestion

val cameraQuiz = Quiz(
    titleResId = R.string.lesson_camera_titile,
    questions = listOf(
        QuizQuestion(
            textResId = R.string.quiz_camera_1,
            answers = listOf(
                QuizAnswer(R.string.quiz_camera_1_1),
                QuizAnswer(R.string.quiz_camera_1_2, isCorrect = true),
                QuizAnswer(R.string.quiz_camera_1_3),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_camera_2,
            answers = listOf(
                QuizAnswer(R.string.quiz_camera_2_1),
                QuizAnswer(R.string.quiz_camera_2_2),
                QuizAnswer(R.string.quiz_camera_2_3, isCorrect = true),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_camera_3,
            answers = listOf(
                QuizAnswer(R.string.quiz_camera_3_1, isCorrect = true),
                QuizAnswer(R.string.quiz_camera_3_2),
                QuizAnswer(R.string.quiz_camera_3_3),
            )
        ),
    )
)