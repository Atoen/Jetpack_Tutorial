package com.abachta.jetpacktutorial.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abachta.jetpacktutorial.ui.AppTheme

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

private val keywordPattern = Regex("\\b(val|var|fun|by|if|else|for|while|when|return|true|false|null)\\b")
private val annotationPattern = Regex("@\\w+")
private val stringPattern = Regex("\"(.*?)\"")
private val commentPattern = Regex("//.*")

@Composable
fun CodeListing(
    code: String,
    modifier: Modifier = Modifier,
    theme: AppTheme = AppTheme.Auto,
) {
    val colors = when (theme) {
        AppTheme.Light -> lightCodeColors
        AppTheme.Dark -> darkCodeColors
        AppTheme.Auto -> if (isSystemInDarkTheme()) darkCodeColors else lightCodeColors
    }

    val style = CodeTokenStyle.create(colors)

    val lines = code.split("\n")
    val annotatedLines = lines.map { line ->
        buildAnnotatedString {
            var currentIndex = 0
            val matches = listOf(
                keywordPattern.findAll(line).toList(),
                stringPattern.findAll(line).toList(),
                annotationPattern.findAll(line).toList(),
                commentPattern.findAll(line).toList()
            ).flatten().sortedBy { it.range.first }

            for (match in matches) {
                append(line.substring(currentIndex, match.range.first))
                when {
                    keywordPattern.matches(match.value) -> withStyle(style.keywords) {
                        append(match.value)
                    }
                    annotationPattern.matches(match.value) -> withStyle(style.annotations) {
                        append(match.value)
                    }
                    stringPattern.matches(match.value) -> withStyle(style.strings) {
                        append(match.value)
                    }
                    commentPattern.matches(match.value) -> withStyle(style.comments) {
                        append(match.value)
                    }
                }
                currentIndex = match.range.last + 1
            }
            append(line.substring(currentIndex))
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .border(1.dp, MaterialTheme.colorScheme.outlineVariant)
            .verticalScroll(rememberScrollState())
            .padding(8.dp)
    ) {
        SelectionContainer {
            Column {
                annotatedLines.forEach { annotatedLine ->
                    Text(
                        text = annotatedLine,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontFamily = FontFamily.Monospace
                        ),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
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
                    val greeting = "Hello, $\name!"
                    Text(text = greeting)
                }
            """.trimIndent(),
        theme = AppTheme.Dark
    )
}