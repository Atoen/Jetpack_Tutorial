package com.abachta.jetpacktutorial.courses.animations.quizzes

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Quiz
import com.abachta.jetpacktutorial.data.QuizAnswer
import com.abachta.jetpacktutorial.data.QuizQuestion

val contentQuiz = Quiz(
    titleResId = R.string.lesson_animated_visibility_title,
    questions = listOf(
        QuizQuestion(
            textResId = R.string.quiz_content_1,
            answers = listOf(
                QuizAnswer(R.string.quiz_content_1_1),
                QuizAnswer(R.string.quiz_content_1_2, isCorrect = true),
                QuizAnswer(R.string.quiz_content_1_3),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_content_2,
            answers = listOf(
                QuizAnswer(R.string.quiz_content_2_1),
                QuizAnswer(R.string.quiz_content_2_2, isCorrect = true),
                QuizAnswer(R.string.quiz_content_2_3),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_content_3,
            answers = listOf(
                QuizAnswer(R.string.quiz_content_3_1),
                QuizAnswer(R.string.quiz_content_3_2, isCorrect = true),
                QuizAnswer(R.string.quiz_content_3_3),
            )
        ),
    )
)