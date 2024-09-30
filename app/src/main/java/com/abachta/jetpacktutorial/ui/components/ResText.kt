package com.abachta.jetpacktutorial.ui.components

import androidx.annotation.StringRes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle

@Composable
fun ResText(
    @StringRes resId: Int,
    modifier: Modifier = Modifier
) {
    val text = stringResource(resId)

    if ('|' in text) {
        Text(
            text = parseMonospaceText(text),
            modifier = modifier
        )
    } else {
        Text(
            text = text,
            modifier = modifier
        )
    }
}

private val pattern = Regex("\\|(.*?)\\|")

fun parseMonospaceText(text: String): AnnotatedString {
    return buildAnnotatedString {
        var currentIndex = 0

        pattern.findAll(text).forEach { match ->
            append(text.substring(currentIndex, match.range.first))

            withStyle(style = SpanStyle(fontFamily = FontFamily.Monospace)) {
                append(match.groupValues[1])
            }

            currentIndex = match.range.last + 1
        }

        append(text.substring(currentIndex))
    }
}