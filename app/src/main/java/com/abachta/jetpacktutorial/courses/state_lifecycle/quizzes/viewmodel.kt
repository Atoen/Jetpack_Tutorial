package com.abachta.jetpacktutorial.courses.state_lifecycle.quizzes

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Quiz
import com.abachta.jetpacktutorial.data.QuizAnswer
import com.abachta.jetpacktutorial.data.QuizQuestion

val viewModelQuiz = Quiz(
    titleResId = R.string.lesson_view_model_title,
    questions = listOf(
        QuizQuestion(
            textResId = R.string.quiz_viewmodel_1,
            answers = listOf(
                QuizAnswer(R.string.quiz_viewmodel_1_1),
                QuizAnswer(R.string.quiz_viewmodel_1_2),
                QuizAnswer(R.string.quiz_viewmodel_1_3),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_viewmodel_2,
            answers = listOf(
                QuizAnswer(R.string.quiz_viewmodel_2_1),
                QuizAnswer(R.string.quiz_viewmodel_2_2),
                QuizAnswer(R.string.quiz_viewmodel_2_3),
            )
        )
    )
)