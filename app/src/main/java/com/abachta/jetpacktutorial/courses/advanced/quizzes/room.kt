package com.abachta.jetpacktutorial.courses.advanced.quizzes

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Quiz
import com.abachta.jetpacktutorial.data.QuizAnswer
import com.abachta.jetpacktutorial.data.QuizQuestion

val roomQuiz = Quiz(
    titleResId = R.string.lesson_room_title,
    questions = listOf(
        QuizQuestion(
            textResId = R.string.quiz_room_1,
            answers = listOf(
                QuizAnswer(R.string.quiz_room_1_1, isCorrect = true),
                QuizAnswer(R.string.quiz_room_1_2),
                QuizAnswer(R.string.quiz_room_1_3),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_room_2,
            answers = listOf(
                QuizAnswer(R.string.quiz_room_2_1),
                QuizAnswer(R.string.quiz_room_2_2),
                QuizAnswer(R.string.quiz_room_2_3, isCorrect = true),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_room_3,
            answers = listOf(
                QuizAnswer(R.string.quiz_room_3_1, isCorrect = true),
                QuizAnswer(R.string.quiz_room_3_2),
                QuizAnswer(R.string.quiz_room_3_3),
            )
        ),
    )
)