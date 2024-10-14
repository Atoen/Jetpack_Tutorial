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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
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
import com.abachta.jetpacktutorial.ui.components.ResText

private val text_1 = LessonPage(
    headingResId = R.string.text_1_heading
) {

    ResText(R.string.text_1_1)

    CodeListing(
        code = """
            @Composable
            fun SimpleText() {
                c-Text("Hello, World!")
            }
        """.trimIndent()
    )

    ResText(R.string.text_1_2)

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Text("Hello, World!")
    }
}

private val text_2 = LessonPage (
    headingResId = R.string.text_2_heading
) {

    ResText(R.string.text_2_1)

    ResText(R.string.text_2_2)

    CodeListing(
        code = """
            @Composable
            fun BigText() {
                c-Text("Hello, World!", color = Color.Blue)
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Text("Hello, World!", color = Color.Blue)
    }

    ResText(R.string.text_2_3)

    CodeListing(
        code = """
            @Composable
            fun BlueText() {
                c-Text("Hello, World!", fontSize = 30.sp)
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Text("Hello, World!", fontSize = 30.sp)
    }
}

private val text_3 = LessonPage(
    headingResId = R.string.text_3_heading
) {

    ResText(R.string.text_3_1)

    CodeListing(
        code = """
            @Composable
            fun MonospaceText() {
                c-Text("Hello, World!", fontFamily = FontFamily.Monospace)
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Text("Hello, World!", fontFamily = FontFamily.Monospace)
    }

    ResText(R.string.text_3_2)

    CodeListing(
        code = """
            @Composable
            fun CustomFontText() {
                val fontFamily = FontFamily(
                    Font(R.font.jacquarda_bastarda_9)
                )
        
                c-Text("Hello, World!", fontFamily = fontFamily)
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        val fontFamily = FontFamily(
            Font(R.font.jacquarda_bastarda_9)
        )

        Text("Hello, World!", fontFamily = fontFamily)
    }
}

private val text_4 = LessonPage(
    headingResId = R.string.text_4_heading
) {

    ResText(R.string.text_4_1)

    CodeListing(
        code = """
            @Composable
            fun ItalicText() {
                c-Text("Hello, World!", fontStyle = FontStyle.Italic)
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Text("Hello, World!", fontStyle = FontStyle.Italic)
    }

    ResText(R.string.text_4_2)

    CodeListing(
        code = """
            @Composable
            fun BoldText() {
                c-Text("Hello, World!", fontWeight = FontWeight.Bold)
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Text("Hello, World!", fontWeight = FontWeight.Bold)
    }

    ResText(R.string.text_4_3)
}

private val text_5 = LessonPage(
    headingResId = R.string.text_5_heading
) {

    ResText(R.string.text_5_1)

    CodeListing(
        code = """
            @Composable
            fun TextStrikeThough() {
                c-Text(
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

    ResText(R.string.text_5_2)

    CodeListing(
        code = """
            @Composable
            fun TextCombinedDecorations() {
                c-Text(
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

private val text_6 = LessonPage(
    headingResId = R.string.text_6_heading
) {

    ResText(R.string.text_6_1)

    CodeListing(
        code = """
            @Composable
            fun TextShadow() {
                val offset = Offset(5.0f, 10.0f)
                c-Text(
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

private val text_7 = LessonPage(
    headingResId = R.string.text_7_heading
) {

    ResText(R.string.text_7_1)

    CodeListing(
        code = """
            @Composable
            fun MultipleStylesInText() {
                c-Text(
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
                append("orld!")
            }
        )
    }
}

private val text_8 = LessonPage(
    headingResId = R.string.text_8_heading
) {

    ResText(R.string.text_8_1)

    CodeListing(
        code = """
            @Composable
            fun LongText() {
                c-Text("hello ".repeat(50), maxLines = 2)
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

    ResText(R.string.text_8_2)

    CodeListing(
        code = """
            @Composable
            fun OverflowedText() {
                c-Text(
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

private val text_9 = LessonPage(
    headingResId = R.string.text_9_heading
) {

    ResText(R.string.text_9_1)

    CodeListing(
        code = """
            @Composable
            fun StringResourceText() {
                c-Text(
                    text = c-stringResource(R.string.hello_world)
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
    text_9
)
