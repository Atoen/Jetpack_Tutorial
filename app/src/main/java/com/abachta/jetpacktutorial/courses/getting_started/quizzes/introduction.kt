package com.abachta.jetpacktutorial.courses.getting_started.quizzes

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Quiz
import com.abachta.jetpacktutorial.data.QuizAnswer
import com.abachta.jetpacktutorial.data.QuizQuestion

val introductionQuiz = Quiz(
    titleResId = R.string.lesson_introduction_title,
    questions = listOf(
        QuizQuestion(
            textResId = R.string.quiz_introduction_1,
            answers = listOf(
                QuizAnswer(R.string.quiz_introduction_1_1),
                QuizAnswer(R.string.quiz_introduction_1_2, isCorrect = true),
                QuizAnswer(R.string.quiz_introduction_1_3)
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_introduction_2,
            answers = listOf(
                QuizAnswer(R.string.quiz_introduction_2_1, isCorrect = true),
                QuizAnswer(R.string.quiz_introduction_2_2),
                QuizAnswer(R.string.quiz_introduction_2_3)
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_introduction_3,
            answers = listOf(
                QuizAnswer(R.string.quiz_introduction_3_1),
                QuizAnswer(R.string.quiz_introduction_3_2, isCorrect = true),
                QuizAnswer(R.string.quiz_introduction_3_3)
            )
        )
    )
)