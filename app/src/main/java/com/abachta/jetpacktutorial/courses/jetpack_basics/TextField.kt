package com.abachta.jetpacktutorial.courses.jetpack_basics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.ui.components.CodeListing
import com.abachta.jetpacktutorial.ui.components.Preview
import com.abachta.jetpacktutorial.ui.components.ResText

private val text_field_1 = LessonPage (
   headingResId = R.string.text_field_1_heading
) {

    ResText(R.string.text_field_1_1)

    CodeListing(
        options = it,
        code = """
            @Composable
            fun SimpleTextField() {
                var text by remember { mutableStateOf("") }
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    placeholder = { Text("Type here") }
                )
                
                Text("entered: ${'$'}text")
            }
        """.trimIndent()
    )

    Preview(
        modifier = Modifier.align(Alignment.CenterHorizontally)
    ) {
        Column {
            var text by remember { mutableStateOf("") }
            TextField(
                value = text,
                onValueChange = { text = it },
                placeholder = { Text("Type here") }
            )

            Text("entered: $text")
        }
    }

    ResText(R.string.text_field_1_2)
}

private val text_field_2 = LessonPage (
   headingResId = R.string.text_field_2_heading
) {

    ResText(R.string.text_field_2_1)

    CodeListing(
        options = it,
        code = """
            ...
            TextField(
                value = text,
                onValueChange = { text = it },
                singleLine = true
            )
            ...
        """.trimIndent()
    )

    Preview(
        modifier = Modifier.align(Alignment.CenterHorizontally)
    ) {
        var text by remember { mutableStateOf("1 line only") }
        TextField(
            value = text,
            onValueChange = { text = it },
            singleLine = true
        )
    }

    CodeListing(
        options = it,
        code = """
            ...
            TextField(
                value = text,
                onValueChange = { text = it },
                minLines = 2,
                maxLines = 3
            )
            ...
        """.trimIndent()
    )

    Preview(
        modifier = Modifier.align(Alignment.CenterHorizontally)
    ) {
        var text by remember { mutableStateOf("2 or\n3\nlines") }
        TextField(
            value = text,
            onValueChange = { text = it },
            minLines = 2,
            maxLines = 3
        )
    }
}

private val text_field_3 = LessonPage (
   headingResId = R.string.text_field_3_heading
) {

    ResText(R.string.text_field_3_1)

    CodeListing(
        options = it,
        code = """
            ...
            TextField(
                value = text,
                onValueChange = { text = it },
                enabled = false
            )
            ...
        """.trimIndent()
    )

    Preview(
        modifier = Modifier.align(Alignment.CenterHorizontally)
    ) {
        var text by remember { mutableStateOf("disabled field") }
        TextField(
            value = text,
            onValueChange = { text = it },
            enabled = false
        )
    }

    CodeListing(
        options = it,
        code = """
            ...
            TextField(
                value = text,
                onValueChange = { text = it },
                readOnly = true
            )
            ...
        """.trimIndent()
    )

    Preview(
        modifier = Modifier.align(Alignment.CenterHorizontally)
    ) {
        var text by remember { mutableStateOf("read only text") }
        TextField(
            value = text,
            onValueChange = { text = it },
            readOnly = true
        )
    }
}

private val text_field_4 = LessonPage (
   headingResId = R.string.text_field_4_heading
) {

    ResText(R.string.text_field_4_1)

    CodeListing(
        options = it,
        code = """
            ...
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Label") },
                supportingText = { Text("Supporting text") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.EditNote,
                        contentDescription = null
                    )
                },
                trailingIcon =  { 
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = null
                    )
                }
            )
            ...
        """.trimIndent()
    )

    Preview(
        modifier = Modifier.align(Alignment.CenterHorizontally)
    ) {
        var text by remember { mutableStateOf("Decorated field") }
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Label") },
            supportingText = { Text("Supporting text") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.EditNote,
                    contentDescription = null
                )
            },
            trailingIcon =  {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = null
                )
            }
        )
    }
}

private val text_field_5 = LessonPage (
   headingResId = R.string.text_field_5_heading
) {

    ResText(R.string.text_field_5_1)

    CodeListing(
        options = it,
        code = """
            ...
        TextField(
            value = text,
            onValueChange = { text = it },
            prefix = { Text("Prefix") },
            suffix = { Text("Suffix") }
        )
            ...
        """.trimIndent()
    )

    Preview(
        modifier = Modifier.align(Alignment.CenterHorizontally)
    ) {
        var text by remember { mutableStateOf("Type here") }
        TextField(
            value = text,
            onValueChange = { text = it },
            prefix = { Text("Prefix") },
            suffix = { Text("Suffix") }
        )
    }
}

private class CustomVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val transformedText = text.text.mapIndexed { i, _ ->
            '0' + (i % 10)
        }.joinToString("")

        return TransformedText(
            AnnotatedString(transformedText),
            OffsetMapping.Identity
        )
    }
}

private val text_field_6 = LessonPage (
   headingResId = R.string.text_field_6_heading
) {

    ResText(R.string.text_field_6_1)

    CodeListing(
        options = it,
        code = """
            ...
            TextField(
                value = text,
                onValueChange = { text = it },
                placeholder = { Text("Enter password") },
                visualTransformation = PasswordVisualTransformation(mask = '\u2022')
            )
            ...
        """.trimIndent()
    )

    Preview(
        modifier = Modifier.align(Alignment.CenterHorizontally)
    ) {
        var text by remember { mutableStateOf("") }
        TextField(
            value = text,
            onValueChange = { text = it },
            placeholder = { Text("Enter password") },
            visualTransformation = PasswordVisualTransformation(mask = '\u2022')
        )
    }

    ResText(R.string.text_field_6_2)

    CodeListing(
        options = it,
        code = """
            class CustomVisualTransformation : VisualTransformation {
                override fun filter(text: AnnotatedString): TransformedText {
                    val transformedText = text.text.mapIndexed { i, _ ->
                        '0' + (i % 10)
                    }.joinToString("")
            
                    return TransformedText(
                        AnnotatedString(transformedText),
                        OffsetMapping.Identity
                    )
                }
            }
        """.trimIndent()
    )

    Preview(
        modifier = Modifier.align(Alignment.CenterHorizontally)
    ) {
        var text by remember { mutableStateOf("") }
        TextField(
            value = text,
            onValueChange = { text = it },
            placeholder = { Text("Custom transformation") },
            visualTransformation = CustomVisualTransformation()
        )
    }
}

private val text_field_7 = LessonPage (
   headingResId = R.string.text_field_7_heading
) {

    ResText(R.string.text_field_7_1)

    CodeListing(
        options = it,
        code = """
            ...
            TextField(
                value = text,
                onValueChange = {
                    text = it.filter {
                        symbol -> symbol.isDigit()
                    }
                },
                placeholder = { Text("Number only") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            ...
        """.trimIndent()
    )

    Preview(
        modifier = Modifier.align(Alignment.CenterHorizontally)
    ) {
        var number by remember { mutableStateOf("") }
        TextField(
            value = number,
            onValueChange = {
                number = it.filter {
                    symbol -> symbol.isDigit()
                }
            },
            placeholder = { Text("Number only") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}

private val text_field_8 = LessonPage (
   headingResId = R.string.text_field_8_heading
) {

    ResText(R.string.text_field_8_1)

    CodeListing(
        options = it,
        code = """
            ...
            TextField(
                value = text,
                onValueChange = { text = it },
                keyboardOptions = KeyboardOptions(
                    imeAction = <action>
                )
            )
            ...
        """.trimIndent()
    )

    Preview(
        modifier = Modifier.align(Alignment.CenterHorizontally)
    ) {
        var text by remember { mutableStateOf("") }
        var currentActionIndex by remember { mutableStateOf(0) }

        val imeActions = listOf(
            ImeAction.Default,
            ImeAction.None,
            ImeAction.Go,
            ImeAction.Search,
            ImeAction.Send,
            ImeAction.Next,
            ImeAction.Done,
            ImeAction.Previous
        )

        Column {
            val keyboardController = LocalSoftwareKeyboardController.current
            Button(
                onClick = {
                    currentActionIndex = (currentActionIndex + 1) % imeActions.size
                    keyboardController?.hide()
                    keyboardController?.show()
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Action: ${imeActions[currentActionIndex]}")
            }

            TextField(
                value = text,
                onValueChange = { text = it },
                keyboardOptions = KeyboardOptions(
                    imeAction = imeActions[currentActionIndex]
                )
            )
        }
    }
}

private val text_field_9 = LessonPage (
   headingResId = R.string.text_field_9_heading
) {

    ResText(R.string.text_field_9_1)

    CodeListing(
        options = it,
        code = """
            ...
            TextField(
                value = text,
                onValueChange = { text = it },
                shape = CutCornerShape(percent = 25),
                colors = TextFieldDefaults.colors().copy(
                    cursorColor = Color.Blue,
                    focusedContainerColor = Color.Yellow,
                    unfocusedContainerColor = Color.White
                ),
                textStyle = TextStyle(fontFamily = FontFamily.Cursive)
            )
            ...
    """.trimIndent()
    )

    Preview(
        modifier = Modifier.align(Alignment.CenterHorizontally)
    ) {
        Column {
            var text by remember { mutableStateOf("") }
            TextField(
                value = text,
                onValueChange = { text = it },
                shape = CutCornerShape(percent = 25),
                colors = TextFieldDefaults.colors().copy(
                    cursorColor = Color.Blue,
                    focusedContainerColor = Color.Yellow,
                    unfocusedContainerColor = Color.White
                ),
                textStyle = TextStyle(fontFamily = FontFamily.Cursive)
            )
        }
    }
}

val textFieldPages = listOf(
    text_field_1,
    text_field_2,
    text_field_3,
    text_field_4,
    text_field_5,
    text_field_6,
    text_field_7,
    text_field_8,
    text_field_9
)