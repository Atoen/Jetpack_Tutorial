package com.abachta.jetpacktutorial.courses.animations.quizzes

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Quiz
import com.abachta.jetpacktutorial.data.QuizAnswer
import com.abachta.jetpacktutorial.data.QuizQuestion

val navTransitionsQuiz = Quiz(
    titleResId = R.string.lesson_nav_transitions_title,
    questions = listOf(
        QuizQuestion(
            textResId = R.string.quiz_nav_transitions_1,
            answers = listOf(
                QuizAnswer(R.string.quiz_nav_transitions_1_1),
                QuizAnswer(R.string.quiz_nav_transitions_1_2),
                QuizAnswer(R.string.quiz_nav_transitions_1_3, isCorrect = true),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_nav_transitions_2,
            answers = listOf(
                QuizAnswer(R.string.quiz_nav_transitions_2_1),
                QuizAnswer(R.string.quiz_nav_transitions_2_2, isCorrect = true),
                QuizAnswer(R.string.quiz_nav_transitions_2_3),
            )
        )
    )
)