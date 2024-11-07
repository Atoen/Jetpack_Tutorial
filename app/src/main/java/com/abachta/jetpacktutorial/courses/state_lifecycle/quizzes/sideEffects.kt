package com.abachta.jetpacktutorial.courses.state_lifecycle.quizzes

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Quiz
import com.abachta.jetpacktutorial.data.QuizAnswer
import com.abachta.jetpacktutorial.data.QuizQuestion

val sideEffectQuiz = Quiz(
    titleResId = R.string.lesson_side_effects_title,
    questions = listOf(
        QuizQuestion(
            textResId = R.string.quiz_side_effects_1,
            answers = listOf(
                QuizAnswer(R.string.quiz_side_effects_1_1),
                QuizAnswer(R.string.quiz_side_effects_1_2),
                QuizAnswer(R.string.quiz_side_effects_1_3, isCorrect = true),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_side_effects_2,
            answers = listOf(
                QuizAnswer(R.string.quiz_side_effects_2_1),
                QuizAnswer(R.string.quiz_side_effects_2_2, isCorrect = true),
                QuizAnswer(R.string.quiz_side_effects_2_3),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_side_effects_3,
            answers = listOf(
                QuizAnswer(R.string.quiz_side_effects_3_1),
                QuizAnswer(R.string.quiz_side_effects_3_2),
                QuizAnswer(R.string.quiz_side_effects_3_3, isCorrect = true),
            )
        ),
    )
)