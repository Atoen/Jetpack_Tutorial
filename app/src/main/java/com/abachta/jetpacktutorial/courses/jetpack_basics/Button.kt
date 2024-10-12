package com.abachta.jetpacktutorial.courses.jetpack_basics

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilledTonalIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.OutlinedIconToggleButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.ui.components.CodeListing
import com.abachta.jetpacktutorial.ui.components.Preview
import com.abachta.jetpacktutorial.ui.components.ResText

private val button_1 = LessonPage(
    headingResId = R.string.button_1_heading
) {

    ResText(R.string.button_1_1)

    CodeListing(
        code = """
            @Composable
            fun ButtonWithText() {
                c-Button(onClick = {}) {
                    c-Text("Button")
                }
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Button(onClick = {}) {
            Text("Button")
        }
    }

    ResText(R.string.button_1_2)

    CodeListing(
        code = """
            @Composable
            fun DisabledButton() {
                c-Button(
                    onClick = {},
                    enabled = false
                ) {
                    c-Text("Button")
                }
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Button(
            onClick = {},
            enabled = false
        ) {
            Text("Button")
        }
    }
}

private val button_2 = LessonPage(
    headingResId = R.string.button_2_heading
) {

    ResText(R.string.button_2_1)

    CodeListing(
        code = """
            @Composable
            fun ButtonWithAction() {
                var count by remember { mutableIntStateOf(0) }
                
                c-Button(onClick = {
                    count++
                }) {
                    c-Text("Count: ${'$'}count")
                }
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        var count by remember { mutableIntStateOf(0) }

        Button(onClick = {
            count++
        }) {
            Text("Count: $count")
        }
    }

    ResText(R.string.button_2_2)
}

private val button_3 = LessonPage (
    headingResId = R.string.button_3_heading
) {

    ResText(R.string.button_3_1)

    CodeListing(
        code = """
            @Composable
            fun ButtonTypes() {
                c-Button(onClick = {}) { 
                    c-Text("Filled")
                }
        
                c-FilledTonalButton (onClick = {}) {
                    c-Text("Tonal")
                }
        
                c-OutlinedButton(onClick = {}) {
                    c-Text("Outlined")
                }
        
                c-ElevatedButton(onClick = {}) {
                    c-Text("Elevated")
                }
        
                c-TextButton(onClick = {}) {
                    c-Text("Text")
                }
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.horizontalScroll(rememberScrollState())
        ) {
            Button(onClick = {}) {
                Text("Filled")
            }

            FilledTonalButton (onClick = {}) {
                Text("Tonal")
            }

            OutlinedButton(onClick = {}) {
                Text("Outlined")
            }

            ElevatedButton(onClick = {}) {
                Text("Elevated")
            }

            TextButton(onClick = {}) {
                Text("Text")
            }
        }
    }
}

private val button_4 = LessonPage (
    headingResId = R.string.button_4_heading
) {

    ResText(R.string.button_4_1)

    CodeListing(
        code = """
            @Composable
            fun IconButtonTypes() {
                c-IconButton (onClick = {}) {
                    c-Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = null
                    )
                }
    
                c-FilledIconButton(onClick = {}) {
                    c-Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = null
                    )
                }
    
                c-OutlinedIconButton(onClick = {}) {
                    c-Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = null
                    )
                }
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.horizontalScroll(rememberScrollState())
        ) {
            IconButton (onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null
                )
            }

            FilledIconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null
                )
            }

            OutlinedIconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null
                )
            }
        }
    }
}

private val button_5 = LessonPage (
    headingResId = R.string.button_5_heading
) {

    ResText(R.string.button_5_1)

    CodeListing(
        code = """
            @Composable
            fun ToggleIconButton() {
                var checked by remember { mutableStateOf(false) }
    
                c-FilledIconToggleButton(
                    checked = checked,
                    onCheckedChange = { checked = !checked }
                ) {
                    c-Icon(
                        imageVector = if (checked) Icons.Filled.Check else Icons.Filled.Close,
                        contentDescription = null
                    )
                }
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.horizontalScroll(rememberScrollState())
        ) {
            var checked1 by remember { mutableStateOf(false) }

            FilledIconToggleButton(
                checked = checked1,
                onCheckedChange = { checked1 = !checked1 }
            ) {
                Icon(
                    imageVector = if (checked1) Icons.Filled.Check else Icons.Filled.Close,
                    contentDescription = null
                )
            }

            var checked2 by remember { mutableStateOf(false) }

            FilledTonalIconToggleButton(
                checked = checked2,
                onCheckedChange = { checked2 = !checked2 }
            ) {
                Icon(
                    imageVector = if (checked2) Icons.Filled.Check else Icons.Filled.Close,
                    contentDescription = null
                )
            }

            var checked3 by remember { mutableStateOf(false) }

            OutlinedIconToggleButton(
                checked = checked3,
                onCheckedChange = { checked3 = !checked3 }
            ) {
                Icon(
                    imageVector = if (checked3) Icons.Filled.Check else Icons.Filled.Close,
                    contentDescription = null
                )
            }
        }
    }
}

private val button_6 = LessonPage (
    headingResId = R.string.button_6_heading
) {

    ResText(R.string.button_6_1)

    CodeListing(
        code = """
            @Composable
            fun ButtonColors() {
                val colors = ButtonColors(
                    contentColor = Color.Red,
                    containerColor = Color.Green,
                    disabledContainerColor = Color.LightGray,
                    disabledContentColor = Color.Blue
                )
                
                c-Button(
                    onClick = {},
                    colors = colors
                ) {
                    c-Text("Colorful")
                }
        
                c-Button(
                    onClick = {},
                    colors = colors,
                    enabled = false
                ) {
                    c-Text("Colorful")
                }
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.horizontalScroll(rememberScrollState())
        ) {
            val colors = ButtonColors(
                contentColor = Color.Red,
                containerColor = Color.Green,
                disabledContainerColor = Color.LightGray,
                disabledContentColor = Color.Blue
            )
            Button(
                onClick = {},
                colors = colors
            ) {
                Text("Colorful")
            }

            Button(
                onClick = {},
                colors = colors,
                enabled = false
            ) {
                Text("Colorful")
            }
        }
    }
}

private val button_7 = LessonPage (
    headingResId = R.string.button_7_heading
) {

    ResText(R.string.button_7_1)

    CodeListing(
        code = """
            @Composable
            fun ShapedButton() {
                c-Button(
                    onClick = {},
                    shape = CutCornerShape(percent = 25),
                    contentPadding = PaddingValues(
                        start = 2.dp,
                        top = 10.dp,
                        end = 16.dp,
                        bottom = 20.dp
                    )
                ) {
                    c-Text("Custom shape")
                }
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Button(
            onClick = {},
            shape = CutCornerShape(percent = 25),
            contentPadding = PaddingValues(
                start = 2.dp,
                top = 10.dp,
                end = 16.dp,
                bottom = 20.dp
            )
        ) {
            Text("Custom shape")
        }
    }
}

private val button_8 = LessonPage (
    headingResId = R.string.button_8_heading
) {

    ResText(R.string.button_8_1)

    CodeListing(
        code = """
            @Composable
            fun ButtonBorders() {
                c-Button(
                    onClick = {},
                    border = BorderStroke(
                        width = 1.dp,
                        color = Color.Red
                    )
                ) {
                    c-Text("Custom shape")
                }
    
                val gradientBrush = Brush.linearGradient(
                    colors = listOf(Color.Red, Color.Green),
                    start = Offset.Zero,
                    end = Offset(100f, 100f)
                )
    
                c-Button(
                    onClick = {},
                    border = BorderStroke(
                        width = 5.dp,
                        brush = gradientBrush
                    )
                ) {
                    c-Text("Custom shape")
                }
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.horizontalScroll(rememberScrollState())
        ) {
            Button(
                onClick = {},
                border = BorderStroke(
                    width = 1.dp,
                    color = Color.Red
                )
            ) {
                Text("Solid")
            }

            val gradientBrush = Brush.linearGradient(
                colors = listOf(Color.Red, Color.Green),
                start = Offset.Zero,
                end = Offset(100f, 100f)
            )

            Button(
                onClick = {},
                border = BorderStroke(
                    width = 5.dp,
                    brush = gradientBrush
                )
            ) {
                Text("Gradient")
            }
        }
    }
}

val buttonPages = listOf(
    button_1,
    button_2,
    button_3,
    button_4,
    button_5,
    button_6,
    button_7,
    button_8
)