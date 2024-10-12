package com.abachta.jetpacktutorial.courses.getting_started.quizzes

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Quiz
import com.abachta.jetpacktutorial.data.QuizAnswer
import com.abachta.jetpacktutorial.data.QuizQuestion

val creatingFirstComposableQuiz = Quiz(
    titleResId = R.string.lesson_creating_first_title,
    questions = listOf(
        QuizQuestion(
            textResId = R.string.quiz_creating_first_1,
            answers = listOf(
                QuizAnswer(R.string.quiz_creating_first_1_1),
                QuizAnswer(R.string.quiz_creating_first_1_2),
                QuizAnswer(R.string.quiz_creating_first_1_3, isCorrect = true),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_creating_first_2,
            answers = listOf(
                QuizAnswer(R.string.quiz_creating_first_2_1, isCorrect = true),
                QuizAnswer(R.string.quiz_creating_first_2_2),
                QuizAnswer(R.string.quiz_creating_first_2_3),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_creating_first_3,
            answers = listOf(
                QuizAnswer(R.string.quiz_creating_first_3_1, isCorrect = true),
                QuizAnswer(R.string.quiz_creating_first_3_2, isCorrect = true),
                QuizAnswer(R.string.quiz_creating_first_3_3)
            )
        ),
    )
)