package com.abachta.jetpacktutorial.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abachta.jetpacktutorial.data.LessonPageOptions
import com.abachta.jetpacktutorial.settings.AppTheme
import com.abachta.jetpacktutorial.settings.CodeListingFont
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private data class CodeColors(
    val keyword: Color,
    val annotation: Color,
    val string: Color,
    val comment: Color
)

private data class CodeTokenStyle(
    val keywords: SpanStyle,
    val annotations: SpanStyle,
    val strings: SpanStyle,
    val comments: SpanStyle
) {
    companion object {
        fun create(colors: CodeColors): CodeTokenStyle {
            return CodeTokenStyle(
                keywords = SpanStyle(color = colors.keyword),
                annotations = SpanStyle(color = colors.annotation),
                strings = SpanStyle(color = colors.string),
                comments = SpanStyle(color = colors.comment, fontStyle = FontStyle.Italic)
            )
        }
    }
}

private val lightCodeColors = CodeColors(
    keyword = Color(0xFF1155D6),
    annotation = Color(0xFF818102),
    string = Color(0xFF0294A2),
    comment =  Color(0xFF717171)
)

private val darkCodeColors = CodeColors(
    keyword = Color(0xFF6B94E9),
    annotation = Color(0xFFBAB429),
    string = Color(0xFF65C1CA),
    comment =  Color(0xFF8F8F8F)
)

private const val keywordPattern = "\\b(val|var|fun|by|if|else|for|while|when|return|true|false|null)\\b"
private const val annotationPattern = "@\\w+"
private const val stringPattern = "\"(.*?)\""
private const val commentPattern = "//.*"

private val combinedPattern = Regex(
    "($annotationPattern)|($stringPattern)|($commentPattern)|$keywordPattern"
)

private fun highlightSyntax(
    code: String,
    style: CodeTokenStyle
): AnnotatedString {
    return buildAnnotatedString {
        var currentIndex = 0
        val matches = combinedPattern.findAll(code)

        for (match in matches) {
            append(code.substring(currentIndex, match.range.first))

            when {
                match.groups[1] != null -> withStyle(style.annotations) { append(match.value) }
                match.groups[2] != null -> withStyle(style.strings) { append(match.value) }
                match.groups[4] != null -> withStyle(style.comments) { append(match.value) }
                match.groups[5] != null -> withStyle(style.keywords) { append(match.value) }
            }

            currentIndex = match.range.last + 1
        }

        append(code.substring(currentIndex))
    }
}

sealed class CodeListingTitlePosition {
    data object Top : CodeListingTitlePosition()
    data object Bottom : CodeListingTitlePosition()
}

@Composable
fun CodeListing(
    code: String,
    modifier: Modifier = Modifier,
    options: LessonPageOptions,
    title: @Composable (() -> Unit)? = null,
    titlePosition: CodeListingTitlePosition = CodeListingTitlePosition.Top
) {
    val colors = when (options.theme) {
        AppTheme.Light -> lightCodeColors
        AppTheme.Dark -> darkCodeColors
        AppTheme.Auto -> if (isSystemInDarkTheme()) darkCodeColors else lightCodeColors
    }

    var parsedCode by remember {
        mutableStateOf(
            buildAnnotatedString {
                append(code)
            }
        )
    }

    LaunchedEffect(code, colors) {
        val style = CodeTokenStyle.create(colors)
        val result = withContext(Dispatchers.Main) {
            highlightSyntax(code, style)
        }
        parsedCode = result
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(8.dp)
    ) {
        if (titlePosition == CodeListingTitlePosition.Top) {
            title?.let { it() }
        }

        SelectionContainer {
            Column {
                Text(
                    text = parsedCode,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontFamily = FontFamily.Monospace,
                    fontSize = options.listingFont.size,
                    lineHeight = options.listingFont.lineHeight
                )
            }
        }

        if (titlePosition == CodeListingTitlePosition.Bottom) {
            title?.let { it() }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CodeListingPreview() {
    CodeListing(
        code = """
                @Composable
                fun Greeting(name: String) {
                    // This is a greeting function with a very long comment text line
                    val greeting = "Hello, ${'$'}name!"
                    Text(text = greeting)
                }
            """.trimIndent(),
        options = LessonPageOptions(
            theme = AppTheme.Light,
            listingFont = CodeListingFont.Medium
        )
    )
}