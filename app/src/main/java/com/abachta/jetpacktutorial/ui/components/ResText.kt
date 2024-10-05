package com.abachta.jetpacktutorial.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abachta.jetpacktutorial.R

@Composable
fun ResText(
    @StringRes resId: Int,
    modifier: Modifier = Modifier
) {
    val text = stringResource(resId)

    if ('|' in text) {
        Text(
            text = parseMonospaceText(text, MaterialTheme.colorScheme.outlineVariant),
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

fun parseMonospaceText(text: String, backgroundColor: Color): AnnotatedString {
    return buildAnnotatedString {
        var currentIndex = 0
        val style = SpanStyle(
            fontFamily = FontFamily.Monospace,
            background = backgroundColor
        )

        pattern.findAll(text).forEach { match ->
            append(text.substring(currentIndex, match.range.first))

            withStyle(style = style) {
                append(match.groupValues[1])
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

        ResText(resId = R.string.monospace_text)
    }
}