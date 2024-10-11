package com.abachta.jetpacktutorial.courses.getting_started.quizzes

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Quiz
import com.abachta.jetpacktutorial.data.QuizAnswer
import com.abachta.jetpacktutorial.data.QuizQuestion

val settingUpEnvironmentQuiz = Quiz(
    titleResId = R.string.lesson_setting_up_environment_title,
    questions = listOf(
        QuizQuestion(
            textResId = R.string.quiz_setting_up_1,
            answers = listOf(
                QuizAnswer(R.string.quiz_setting_up_1_1),
                QuizAnswer(R.string.quiz_setting_up_1_2),
                QuizAnswer(R.string.quiz_setting_up_1_3, isCorrect = true)
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_setting_up_2,
            answers = listOf(
                QuizAnswer(R.string.quiz_setting_up_2_1),
                QuizAnswer(R.string.quiz_setting_up_2_2, isCorrect = true),
                QuizAnswer(R.string.quiz_setting_up_2_3)
            )
        )
    )
)