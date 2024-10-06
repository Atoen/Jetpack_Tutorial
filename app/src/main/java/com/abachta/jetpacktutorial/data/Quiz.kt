package com.abachta.jetpacktutorial.data

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable

data class Quiz(
    @StringRes val titleResId: Int,
    val questions: List<QuizQuestion>,
    val id: QuizId = QuizId.next()
)

@JvmInline
value class QuizId(val value: Int) {
    companion object {

        const val START_ID = 1

        private var current: Int = START_ID

        fun next() = synchronized(this) {
            QuizId(current++)
        }
    }
}

class QuizQuestion(
    @StringRes val textResId: Int,
    val content: (@Composable () -> Unit)? = null,
    val answers: List<QuizAnswer>
)

data class QuizAnswer(
    @StringRes
    val textResId: Int,
    val isCorrect: Boolean = false
)