package com.abachta.jetpacktutorial.courses.layout.quizzes

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Quiz
import com.abachta.jetpacktutorial.data.QuizAnswer
import com.abachta.jetpacktutorial.data.QuizQuestion

val scaffoldQuiz = Quiz(
    titleResId = R.string.lesson_scaffold_title,
    questions = listOf(
        QuizQuestion(
            textResId = R.string.quiz_scaffold_1,
            answers = listOf(
                QuizAnswer(R.string.quiz_scaffold_1_1, isCorrect = true),
                QuizAnswer(R.string.quiz_scaffold_1_2),
                QuizAnswer(R.string.quiz_scaffold_1_3, isCorrect = true),
                QuizAnswer(R.string.quiz_scaffold_1_4, isCorrect = true),
                QuizAnswer(R.string.quiz_scaffold_1_5),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_scaffold_2,
            answers = listOf(
                QuizAnswer(R.string.quiz_scaffold_2_1),
                QuizAnswer(R.string.quiz_scaffold_2_2),
                QuizAnswer(R.string.quiz_scaffold_2_3, isCorrect = true),
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_scaffold_3,
            answers = listOf(
                QuizAnswer(R.string.quiz_scaffold_3_1),
                QuizAnswer(R.string.quiz_scaffold_3_2, isCorrect = true),
                QuizAnswer(R.string.quiz_scaffold_3_3),
            )
        ),
    )
)