package com.abachta.jetpacktutorial.courses.jetpack_basics.challenges

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.CodeChallenge

val buttonChallenge = CodeChallenge(
    titleResId = R.string.lesson_button_title,
    content = {
        FilledTonalButton(
            border = BorderStroke(width = 2.dp, color = Color.Red),
            onClick = {}
        ) {
            Text("Filled tonal button")
        }
    },
    code = """
        c-FilledTonalButton(
            border = BorderStroke(
                width = 2.dp,
                color = Color.Red
            ),
            onClick = {}
        ) {
            c-Text("Filled tonal button")
        }
    """.trimIndent()
)