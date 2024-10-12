package com.abachta.jetpacktutorial.courses.jetpack_basics.quizzes

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Quiz
import com.abachta.jetpacktutorial.data.QuizAnswer
import com.abachta.jetpacktutorial.data.QuizQuestion

val buttonQuiz = Quiz(
    titleResId = R.string.lesson_button_title,
    questions = listOf(
        QuizQuestion(
            textResId = R.string.quiz_button_1,
            answers = listOf(
                QuizAnswer(R.string.quiz_button_1_1, isCorrect = true),
                QuizAnswer(R.string.quiz_button_1_2),
                QuizAnswer(R.string.quiz_button_1_3),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_button_2,
            answers = listOf(
                QuizAnswer(R.string.quiz_button_2_1),
                QuizAnswer(R.string.quiz_button_2_2, isCorrect = true),
                QuizAnswer(R.string.quiz_button_2_3),
            )
        ),
    )
)