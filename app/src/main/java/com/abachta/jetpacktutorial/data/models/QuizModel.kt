package com.abachta.jetpacktutorial.data.models

import androidx.annotation.FloatRange
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.abachta.jetpacktutorial.data.Quiz
import com.abachta.jetpacktutorial.data.QuizAnswer
import com.abachta.jetpacktutorial.data.QuizId
import com.abachta.jetpacktutorial.data.QuizQuestion

class QuizModel(
    @StringRes val titleResId: Int,
    val questions: List<QuizQuestionModel>,
    val id: QuizId
) {
    fun reset() {
        questions.forEach { it.reset() }
    }

    fun incorrectAnswerCount(): Int {
        return questions.sumOf {
            it.getIncorrectAnswerCount()
        }
    }

    fun correctlyAnsweredQuestionCount(): Int {
        return questions.count {
            it.getIncorrectAnswerCount() == 0
        }
    }

    @FloatRange(from = 0.0, to = 1.0)
    fun correctlyAnsweredQuestionFraction(): Float {
        return questions.count {
            it.getIncorrectAnswerCount() == 0
        } / questions.count().toFloat()
    }

    companion object {
        fun crate(quiz: Quiz) = QuizModel(
            titleResId = quiz.titleResId,
            questions = quiz.questions.map(QuizQuestionModel::create),
            id = quiz.id
        )
    }
}

class QuizQuestionModel(
    @StringRes val textResId: Int,
    val content: (@Composable () -> Unit)?,
    val answers: List<QuizAnswerModel>
) {
    var revealed by mutableStateOf(false)

    fun reveal() {
        revealed = true
    }

    fun reset() {
        revealed = false
        answers.forEach { it.reset() }
    }

    fun getIncorrectAnswerCount(): Int {
        return answers.count {
            !it.selectedCorrectly
        }
    }

    companion object {
        fun create(quizQuestion: QuizQuestion) = QuizQuestionModel(
            textResId = quizQuestion.textResId,
            content = quizQuestion.content,
            answers = quizQuestion.answers.map(QuizAnswerModel::create)
        )
    }
}

class QuizAnswerModel(
    @StringRes val textResId: Int,
    val isCorrect: Boolean
) {
    var selected by mutableStateOf(false)

    val selectedCorrectly: Boolean
        get() = isCorrect == selected

    fun reset() {
        selected = false
    }

    companion object {
        fun create(quizAnswer: QuizAnswer) = QuizAnswerModel(
            textResId = quizAnswer.textResId,
            isCorrect = quizAnswer.isCorrect
        )
    }
}