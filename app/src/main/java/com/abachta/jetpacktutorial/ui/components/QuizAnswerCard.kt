package com.abachta.jetpacktutorial.ui.components

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import com.abachta.jetpacktutorial.R

private enum class AnswerType {
    UnknownSelected,
    UnknownUnselected,
    CorrectSelected,
    CorrectUnselected,
    IncorrectSelected,
    IncorrectUnselected
}

@Composable
@ReadOnlyComposable
private fun AnswerType.getCardColor(): Color = when (this) {
    AnswerType.UnknownSelected -> colorScheme.secondaryContainer
    AnswerType.UnknownUnselected -> colorScheme.surfaceContainer
    AnswerType.CorrectSelected -> colorScheme.primaryContainer
    AnswerType.CorrectUnselected -> colorScheme.surfaceContainer
    AnswerType.IncorrectSelected -> colorScheme.errorContainer
    AnswerType.IncorrectUnselected -> colorScheme.surfaceContainer
}

@Composable
@ReadOnlyComposable
private fun AnswerType.getContentColor(): Color = when (this) {
    AnswerType.CorrectSelected -> colorScheme.primary
    AnswerType.IncorrectSelected, AnswerType.IncorrectUnselected -> colorScheme.error
    else -> LocalContentColor.current
}

private fun AnswerType.shouldDisplayIcon(): Boolean =
    this == AnswerType.CorrectSelected ||
    this == AnswerType.IncorrectUnselected ||
    this == AnswerType.IncorrectSelected

@Composable
fun QuizAnswerCard(
    @StringRes textResId: Int,
    isSelected: Boolean,
    isCorrect: Boolean,
    isRevealed: Boolean = true,
    onClick: () -> Unit
) {
    val answerType = when {
        !isRevealed && isSelected -> AnswerType.UnknownSelected
        !isRevealed && !isSelected -> AnswerType.UnknownUnselected
        isSelected && isCorrect -> AnswerType.CorrectSelected
        isSelected && !isCorrect -> AnswerType.IncorrectSelected
        !isSelected && isCorrect -> AnswerType.IncorrectUnselected
        else -> AnswerType.CorrectUnselected
    }

    ElevatedCard(
        onClick = onClick,
        colors = CardDefaults.elevatedCardColors(
            containerColor = answerType.getCardColor(),
            contentColor = answerType.getContentColor()
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .then(if (answerType == AnswerType.IncorrectUnselected) {
                Modifier.border(
                    width = 1.5f.dp,
                    color = colorScheme.error,
                    shape = CardDefaults.elevatedShape
                )
            } else Modifier)

    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(textResId),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)
            )

            if (answerType.shouldDisplayIcon()) {

                val icon = if (isCorrect) Icons.Filled.Check else Icons.Filled.Close

                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterVertically)
                )
            }
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    showSystemUi = false,
    wallpaper = Wallpapers.NONE, showBackground = true,
    device = "spec:width=1080px,height=2340px,dpi=440"
)
@Composable
private fun QuizAnswerCardPreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        QuizAnswerCard(
            textResId = R.string.quiz_answer_1_1,
            isSelected = true,
            isCorrect = false,
            isRevealed = false,
            onClick = { }
        )

        QuizAnswerCard(
            textResId = R.string.quiz_answer_1_2,
            isSelected = false,
            isCorrect = false,
            isRevealed = false,
            onClick = { }
        )

        QuizAnswerCard(
            textResId = R.string.quiz_answer_1_1,
            isSelected = true,
            isCorrect = true,
            onClick = { }
        )

        QuizAnswerCard(
            textResId = R.string.quiz_answer_1_2,
            isSelected = true,
            isCorrect = false,
            onClick = { }
        )

        QuizAnswerCard(
            textResId = R.string.quiz_answer_1_3,
            isSelected = false,
            isCorrect = true,
            onClick = { }
        )

        QuizAnswerCard(
            textResId = R.string.quiz_answer_1_3,
            isSelected = false,
            isCorrect = false,
            onClick = { }
        )
    }
}