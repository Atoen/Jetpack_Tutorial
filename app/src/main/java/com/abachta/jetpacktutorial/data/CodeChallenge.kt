package com.abachta.jetpacktutorial.data

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable

data class CodeChallenge(
    @StringRes val titleResId: Int,
    val content: @Composable () -> Unit,
    val code: String,
    val id: ChallengeId = ChallengeId.next()
)

@JvmInline
value class ChallengeId(val value: Int) {
    companion object {

        const val START_ID = 1

        private var current: Int = START_ID

        fun next() = synchronized(this) {
            ChallengeId(current++)
        }
    }
}
