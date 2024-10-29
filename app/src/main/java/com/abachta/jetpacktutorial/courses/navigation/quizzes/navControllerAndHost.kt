package com.abachta.jetpacktutorial.courses.navigation.quizzes

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Quiz
import com.abachta.jetpacktutorial.data.QuizAnswer
import com.abachta.jetpacktutorial.data.QuizQuestion

val navControllerAndHostQuiz = Quiz(
    titleResId = R.string.lesson_nav_ctr_host_title,
    questions = listOf(
        QuizQuestion(
            textResId = R.string.quiz_nav_ctr_host_1,
            answers = listOf(
                QuizAnswer(R.string.quiz_nav_ctr_host_1_1),
                QuizAnswer(R.string.quiz_nav_ctr_host_1_2),
                QuizAnswer(R.string.quiz_nav_ctr_host_1_3, isCorrect = true),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_nav_ctr_host_2,
            answers = listOf(
                QuizAnswer(R.string.quiz_nav_ctr_host_2_1),
                QuizAnswer(R.string.quiz_nav_ctr_host_2_2, isCorrect = true),
                QuizAnswer(R.string.quiz_nav_ctr_host_2_3),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_nav_ctr_host_3,
            answers = listOf(
                QuizAnswer(R.string.quiz_nav_ctr_host_3_1, isCorrect = true),
                QuizAnswer(R.string.quiz_nav_ctr_host_3_2),
                QuizAnswer(R.string.quiz_nav_ctr_host_3_3),
            )
        ),
    )
)