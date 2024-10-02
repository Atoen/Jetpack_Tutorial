package com.abachta.jetpacktutorial.data

import androidx.annotation.FloatRange
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class Quiz(
    @StringRes val titleResId: Int,
    val questions: List<QuizQuestion>,
    val id: Int = 0
) {
    fun reset() {
        questions.forEach {
            it.reset()
        }
    }

    fun incorrectAnswerCount(): Int {
        return questions.sumOf {
            it.getIncorrectAnswerCount()
        }
    }

    @FloatRange(from = 0.0, to = 1.0)
    fun correctlyAnsweredQuestionFraction(): Float {
        return questions.count {
            it.getIncorrectAnswerCount() == 0
        } / questions.count().toFloat()
    }
}

class QuizQuestion(
    @StringRes
    val textResId: Int,
    val content: (@Composable () -> Unit)? = null,
    val answers: List<QuizAnswer>
) {
    private val state = QuizQuestionState()

    var revealed
        get() = state.revealed
        private set(value) { state.revealed = value }

    fun reset() {
        revealed = false
        answers.forEach {
            it.reset()
        }
    }

    fun reveal() {
        revealed = true
    }

    fun getIncorrectAnswerCount(): Int {
        return answers.count {
            !it.selectedCorrectly
        }
    }
}

class QuizQuestionState {
    var revealed by mutableStateOf(false)
}

data class QuizAnswer(
    @StringRes
    val textResId: Int,
    val isCorrect: Boolean = false
) {
    private val state = QuizAnswerState()

    var selected
        get() = state.selected
        set(value) { state.selected = value }

    val selectedCorrectly: Boolean
        get() = isCorrect == selected

    fun reset() {
        selected = false
    }
}

class QuizAnswerState {
    var selected by mutableStateOf(false)
}