package com.abachta.jetpacktutorial.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abachta.jetpacktutorial.R

@Composable
fun ResText(
    @StringRes resId: Int,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current
) {
    val text = stringResource(resId)

    if ('|' in text || '*' in text) {
        Text(
            text = parseFormattedText(text, MaterialTheme.colorScheme.outlineVariant),
            modifier = modifier,
            style = style
        )
    } else {
        Text(
            text = text,
            modifier = modifier,
            style = style
        )
    }
}

private const val monospacePattern = "\\|(.*?)\\|"
private const val boldPattern = "\\*(.*?)\\*"

private val combinedPattern = Regex(
    "$monospacePattern|$boldPattern"
)

private val boldStyle = SpanStyle(fontWeight = FontWeight.Bold)

fun parseFormattedText(text: String, backgroundColor: Color): AnnotatedString {
    return buildAnnotatedString {
        var currentIndex = 0
        val monospaceStyle = SpanStyle(
            fontFamily = FontFamily.Monospace,
            background = backgroundColor
        )

        combinedPattern.findAll(text).forEach { match ->
            append(text.substring(currentIndex, match.range.first))

            when {
                match.groups[1] != null -> withStyle(monospaceStyle) { append(match.groupValues[1]) }
                match.groups[2] != null -> withStyle(boldStyle) { append(match.groupValues[2]) }
            }

            currentIndex = match.range.last + 1
        }

        append(text.substring(currentIndex))
    }
}

@Preview(showBackground = true)
@Composable
private fun ResTextPreview() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        ResText(resId = R.string.regular_text)

        Spacer(modifier = Modifier.height(16.dp))

        ResText(resId = R.string.formatted_text)
    }
}