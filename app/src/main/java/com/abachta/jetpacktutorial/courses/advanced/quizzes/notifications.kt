package com.abachta.jetpacktutorial.courses.advanced.quizzes

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Quiz
import com.abachta.jetpacktutorial.data.QuizAnswer
import com.abachta.jetpacktutorial.data.QuizQuestion

val notificationQuiz = Quiz(
    titleResId = R.string.lesson_notification_title,
    questions = listOf(
        QuizQuestion(
            textResId = R.string.quiz_notification_1,
            answers = listOf(
                QuizAnswer(R.string.quiz_notification_1_1),
                QuizAnswer(R.string.quiz_notification_1_2),
                QuizAnswer(R.string.quiz_notification_1_3, isCorrect = true),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_notification_2,
            answers = listOf(
                QuizAnswer(R.string.quiz_notification_2_1),
                QuizAnswer(R.string.quiz_notification_2_2),
                QuizAnswer(R.string.quiz_notification_2_3, isCorrect = true),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_notification_3,
            answers = listOf(
                QuizAnswer(R.string.quiz_notification_3_1, isCorrect = true),
                QuizAnswer(R.string.quiz_notification_3_2),
                QuizAnswer(R.string.quiz_notification_3_3),
            )
        ),
    )
)