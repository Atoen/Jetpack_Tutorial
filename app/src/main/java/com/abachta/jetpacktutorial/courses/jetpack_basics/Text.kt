package com.abachta.jetpacktutorial.courses.jetpack_basics

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.ui.components.CodeListing
import com.abachta.jetpacktutorial.ui.components.Preview

val text_1 = LessonPage(
    headingResId = R.string.text_1_heading
) {

    Text(stringResource(R.string.text_1_1))

    CodeListing(
        options = it,
        code = """
            @Composable
            fun SimpleText() {
                Text("Hello, World!")
            }
        """.trimIndent()
    )

    Text(stringResource(R.string.text_1_2))

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Text("Hello, World!")
    }
}

val text_2 = LessonPage (
    headingResId = R.string.text_2_heading
) {
    Text(stringResource(R.string.text_2_1))

    Text(stringResource(R.string.text_2_2))

    CodeListing(
        options = it,
        code = """
            @Composable
            fun BigText() {
                Text("Hello, World", color = Color.Blue)
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Text("Hello, World", color = Color.Blue)
    }

    Text(stringResource(R.string.text_2_3))

    CodeListing(
        options = it,
        code = """
            @Composable
            fun BlueText() {
                Text("Hello, World", fontSize = 30.sp)
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Text("Hello, World", fontSize = 30.sp)
    }
}

val text_3 = LessonPage(
    headingResId = R.string.text_3_heading
) {

    Text(stringResource(R.string.text_3_1))

    CodeListing(
        options = it,
        code = """
            @Composable
            fun ItalicText() {
                Text("Hello, World", fontStyle = FontStyle.Italic)
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Text("Hello, World", fontStyle = FontStyle.Italic)
    }

    Text(stringResource(R.string.text_3_2))

    CodeListing(
        options = it,
        code = """
            @Composable
            fun BoldText() {
                Text("Hello, World", fontWeight = FontWeight.Bold)
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Text("Hello, World", fontWeight = FontWeight.Bold)
    }

    Text(stringResource(R.string.text_3_3))
}

val text_4 = LessonPage(
    headingResId = R.string.text_4_heading
) {

    Text(stringResource(R.string.text_4_1))

    CodeListing(
        options = it,
        code = """
            @Composable
            fun TextStrikeThough() {
                Text(
                    text = "Hello, world!",
                    textDecoration = TextDecoration.LineThrough
                )
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Text(
            text = "Hello, world!",
            textDecoration = TextDecoration.LineThrough
        )
    }

    Text(stringResource(R.string.text_4_2))

    CodeListing(
        options = it,
        code = """
            @Composable
            fun TextCombinedDecorations() {
                Text(
                    text = "Hello, world!",
                    textDecoration = TextDecoration.LineThrough + TextDecoration.Underline
                )
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Text(
            text = "Hello, world!",
            textDecoration = TextDecoration.LineThrough + TextDecoration.Underline
        )
    }
}

val text_5 = LessonPage(
    headingResId = R.string.text_5_heading
) {

    Text(stringResource(R.string.text_5_1))

    CodeListing(
        options = it,
        code = """
            @Composable
            fun TextShadow() {
                val offset = Offset(5.0f, 10.0f)
                Text(
                    text = "Hello, world!",
                    style = TextStyle(
                        fontSize = 24.sp,
                        shadow = Shadow(
                            color = Color.Blue, offset = offset, blurRadius = 3f
                        )
                    )
                )
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        val offset = Offset(5.0f, 10.0f)
        Text(
            text = "Hello, world!",
            style = TextStyle(
                fontSize = 24.sp,
                shadow = Shadow(
                    color = Color.Blue, offset = offset, blurRadius = 3f
                )
            )
        )
    }
}

val text_6 = LessonPage(
    headingResId = R.string.text_6_heading
) {

    Text(stringResource(R.string.text_6_1))

    CodeListing(
        options = it,
        code = """
            @Composable
            fun MultipleStylesInText() {
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.Blue)) {
                            append("H")
                        }
                        append("ello, ")
            
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Red)) {
                            append("W")
                        }
                        append("orld")
                    }
                )
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Blue)) {
                    append("H")
                }
                append("ello, ")

                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Red)) {
                    append("W")
                }
                append("orld")
            }
        )
    }
}

val text_7 = LessonPage(
    headingResId = R.string.text_7_heading
) {
    Text(stringResource(R.string.text_7_1))

    CodeListing(
        options = it,
        code = """
            @Composable
            fun LongText() {
                Text("hello ".repeat(50), maxLines = 2)
            }
        """.trimIndent()
    )

    Preview(
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .width(220.dp)
    ) {
        Text("hello ".repeat(50), maxLines = 2)
    }

    Text(stringResource(R.string.text_7_2))

    CodeListing(
        options = it,

        code = """
            @Composable
            fun OverflowedText() {
                Text(
                    text = "Hello World ".repeat(50),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        """.trimIndent()
    )

    Preview(
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .width(220.dp)
    ) {
        Text(
            text = "Hello World ".repeat(50),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

val text_8 = LessonPage(
    headingResId = R.string.text_8_heading
) {

    Text(stringResource(R.string.text_8_1))

    CodeListing(
        options = it,
        code = """
            @Composable
            fun StringResourceText() {
                Text(
                    text = stringResource(R.string.hello_world)
                )
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Text(
            text = stringResource(R.string.hello_world)
        )
    }
}

val textPages = listOf(
    text_1,
    text_2,
    text_3,
    text_4,
    text_5,
    text_6,
    text_7,
    text_8,
)
