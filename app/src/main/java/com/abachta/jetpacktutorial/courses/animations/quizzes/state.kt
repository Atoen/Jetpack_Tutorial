package com.abachta.jetpacktutorial.courses.animations.quizzes

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Quiz
import com.abachta.jetpacktutorial.data.QuizAnswer
import com.abachta.jetpacktutorial.data.QuizQuestion

val stateQuiz = Quiz(
    titleResId = R.string.lesson_animate_values_title,
    questions = listOf(
        QuizQuestion(
            textResId = R.string.quiz_state_1,
            answers = listOf(
                QuizAnswer(R.string.quiz_state_1_1),
                QuizAnswer(R.string.quiz_state_1_2, isCorrect = true),
                QuizAnswer(R.string.quiz_state_1_3),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_state_2,
            answers = listOf(
                QuizAnswer(R.string.quiz_state_2_1),
                QuizAnswer(R.string.quiz_state_2_2, isCorrect = true),
                QuizAnswer(R.string.quiz_state_2_3),
            )
        ),
    )
)