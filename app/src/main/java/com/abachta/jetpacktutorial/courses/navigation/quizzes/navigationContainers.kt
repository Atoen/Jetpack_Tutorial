package com.abachta.jetpacktutorial.courses.navigation.quizzes

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Quiz
import com.abachta.jetpacktutorial.data.QuizAnswer
import com.abachta.jetpacktutorial.data.QuizQuestion

val navigationContainerQuiz = Quiz(
    titleResId = R.string.lesson_nav_containers_title,
    questions = listOf(
        QuizQuestion(
            textResId = R.string.quiz_nav_containers_1,
            answers = listOf(
                QuizAnswer(R.string.quiz_nav_containers_1_1),
                QuizAnswer(R.string.quiz_nav_containers_1_2, isCorrect = true),
                QuizAnswer(R.string.quiz_nav_containers_1_3),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_nav_containers_2,
            answers = listOf(
                QuizAnswer(R.string.quiz_nav_containers_2_1),
                QuizAnswer(R.string.quiz_nav_containers_2_2),
                QuizAnswer(R.string.quiz_nav_containers_2_3, isCorrect = true),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_nav_containers_3,
            answers = listOf(
                QuizAnswer(R.string.quiz_nav_containers_3_1, isCorrect = true),
                QuizAnswer(R.string.quiz_nav_containers_3_2),
                QuizAnswer(R.string.quiz_nav_containers_3_3),
            )
        ),
    )
)