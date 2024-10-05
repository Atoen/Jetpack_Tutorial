package com.abachta.jetpacktutorial.settings

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.stringResource
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.ui.components.SelectableTextProvider

sealed class QuizShufflingOption(
    val shuffleQuestions: Boolean,
    val shuffleAnswers: Boolean,
    val value: Int,
    @StringRes val displayNameResId: Int
) : SelectableTextProvider {

    data object NoShuffle : QuizShufflingOption(false, false, 0, R.string.no_shuffle)
    data object ShuffleQuestions: QuizShufflingOption(true, false, 1, R.string.shuffle_questions)
    data object ShuffleAll : QuizShufflingOption(true, true, 2, R.string.shuffle_all)

    @Composable
    @ReadOnlyComposable
    override fun getText(): String = stringResource(displayNameResId)

    companion object {

        val entries by lazy {
            listOf(NoShuffle, ShuffleQuestions, ShuffleAll)
        }

        fun fromInt(int: Int): QuizShufflingOption {
            return when (int) {
                0 -> NoShuffle
                1 -> ShuffleQuestions
                2 -> ShuffleAll
                else -> ShuffleQuestions
            }
        }
    }
}