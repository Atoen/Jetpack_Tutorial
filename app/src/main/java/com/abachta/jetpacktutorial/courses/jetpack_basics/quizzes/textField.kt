package com.abachta.jetpacktutorial.courses.jetpack_basics.quizzes

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Quiz
import com.abachta.jetpacktutorial.data.QuizAnswer
import com.abachta.jetpacktutorial.data.QuizQuestion

val textFieldQuiz = Quiz(
    titleResId = R.string.lesson_text_field_title,
    questions = listOf(
        QuizQuestion(
            textResId = R.string.quiz_text_field_1,
            answers = listOf(
                QuizAnswer(R.string.quiz_text_field_1_1, isCorrect = true),
                QuizAnswer(R.string.quiz_text_field_1_2),
                QuizAnswer(R.string.quiz_text_field_1_3),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_text_field_2,
            answers = listOf(
                QuizAnswer(R.string.quiz_text_field_2_1),
                QuizAnswer(R.string.quiz_text_field_2_2, isCorrect = true),
                QuizAnswer(R.string.quiz_text_field_2_3),
                QuizAnswer(R.string.quiz_text_field_2_4),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_text_field_3,
            answers = listOf(
                QuizAnswer(R.string.quiz_text_field_3_1, isCorrect = true),
                QuizAnswer(R.string.quiz_text_field_3_2),
                QuizAnswer(R.string.quiz_text_field_3_3),
            )
        ),
    )
)