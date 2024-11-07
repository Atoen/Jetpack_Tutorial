package com.abachta.jetpacktutorial.courses.layout.quizzes

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Quiz
import com.abachta.jetpacktutorial.data.QuizAnswer
import com.abachta.jetpacktutorial.data.QuizQuestion

val pagerQuiz = Quiz(
    titleResId = R.string.lesson_pager_title,
    questions = listOf(
        QuizQuestion(
            textResId = R.string.quiz_pager_1,
            answers = listOf(
                QuizAnswer(R.string.quiz_pager_1_1),
                QuizAnswer(R.string.quiz_pager_1_2, isCorrect = true),
                QuizAnswer(R.string.quiz_pager_1_3),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_pager_2,
            answers = listOf(
                QuizAnswer(R.string.quiz_pager_2_1),
                QuizAnswer(R.string.quiz_pager_2_2, isCorrect = true),
                QuizAnswer(R.string.quiz_pager_2_3),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_pager_3,
            answers = listOf(
                QuizAnswer(R.string.quiz_pager_3_1),
                QuizAnswer(R.string.quiz_pager_3_2, isCorrect = true),
                QuizAnswer(R.string.quiz_pager_3_3),
            )
        ),
    )
)