package com.abachta.jetpacktutorial.courses.jetpack_basics.quizzes

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Quiz
import com.abachta.jetpacktutorial.data.QuizAnswer
import com.abachta.jetpacktutorial.data.QuizQuestion

val modifierQuiz = Quiz(
    titleResId = R.string.lesson_modifier_title,
    questions = listOf(
        QuizQuestion(
            textResId = R.string.quiz_modifier_1,
            answers = listOf(
                QuizAnswer(R.string.quiz_modifier_1_1),
                QuizAnswer(R.string.quiz_modifier_1_2),
                QuizAnswer(R.string.quiz_modifier_1_3, isCorrect = true),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_modifier_2,
            answers = listOf(
                QuizAnswer(R.string.quiz_modifier_2_1, isCorrect = true),
                QuizAnswer(R.string.quiz_modifier_2_2),
                QuizAnswer(R.string.quiz_modifier_2_3),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_modifier_3,
            answers = listOf(
                QuizAnswer(R.string.quiz_modifier_3_1),
                QuizAnswer(R.string.quiz_modifier_3_2, isCorrect = true),
                QuizAnswer(R.string.quiz_modifier_3_3),
            )
        ),
    )
)