package com.abachta.jetpacktutorial.courses.jetpack_basics.quizzes

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Quiz
import com.abachta.jetpacktutorial.data.QuizAnswer
import com.abachta.jetpacktutorial.data.QuizQuestion

val textQuiz = Quiz(
    titleResId = R.string.lesson_text_title,
    questions = listOf(
        QuizQuestion(
            textResId = R.string.quiz_question_1,
            answers = listOf(
                QuizAnswer(textResId = R.string.quiz_answer_1_1, isCorrect = true),
                QuizAnswer(textResId = R.string.quiz_answer_1_2),
                QuizAnswer(textResId = R.string.quiz_answer_1_3)
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_question_2,
            answers = listOf(
                QuizAnswer(textResId = R.string.quiz_answer_2_1),
                QuizAnswer(textResId = R.string.quiz_answer_2_2, isCorrect = true),
                QuizAnswer(textResId = R.string.quiz_answer_2_3)
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_question_3,
            answers = listOf(
                QuizAnswer(textResId = R.string.quiz_answer_3_1),
                QuizAnswer(textResId = R.string.quiz_answer_3_2),
                QuizAnswer(textResId = R.string.quiz_answer_3_3, isCorrect = true)
            )
        )
    )
)