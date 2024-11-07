package com.abachta.jetpacktutorial.courses.jetpack_basics.challenges

import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.CodeChallenge

val textChallenge = CodeChallenge(
    titleResId = R.string.lesson_text_title,
    content = {
        Text(
            text = "Underlined red text in cursive",
            color = Color.Red,
            fontSize = 32.sp,
            fontFamily = FontFamily.Cursive,
            textDecoration = TextDecoration.Underline
        )
    },
    code = """
        c-Text(
            text = "Underlined red text in cursive",
            color = Color.Red,
            fontSize = 32.sp,
            fontFamily = FontFamily.Cursive,
            textDecoration = TextDecoration.Underline
        )
    """.trimIndent()
)