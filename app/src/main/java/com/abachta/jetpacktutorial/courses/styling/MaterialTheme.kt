package com.abachta.jetpacktutorial.courses.styling

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.ui.components.CodeListing
import com.abachta.jetpacktutorial.ui.components.Preview
import com.abachta.jetpacktutorial.ui.components.ResText

private val material_theme_1 = LessonPage (
    headingResId = R.string.material_theme_1_heading
) {

    ResText(R.string.material_theme_1_1)

    CodeListing(
        code = """
            val md_theme_light_primary = Color(0xFF825500)
            val md_theme_light_onPrimary = Color(0xFFFFFFFF)
            val md_theme_light_primaryContainer = Color(0xFFFFDDB3)
            // rest of the colors ...
        """.trimIndent()
    )

    ResText(R.string.material_theme_1_2)

    CodeListing(
        code = """
            private val Colors = lightColorScheme(
                primary = md_theme_light_primary,
                onPrimary = md_theme_light_onPrimary,
                primaryContainer = md_theme_light_primaryContainer,
                // ...
            )
            
            @Composable
            fun AppTheme(
                content: @Composable () -> Unit
            ) {
                c-MaterialTheme(
                    colorScheme = Colors,
                    content = content
                )
            }
        """.trimIndent()
    )

    ResText(R.string.material_theme_1_3)
}

private val material_theme_2 = LessonPage (
    headingResId = R.string.material_theme_2_heading
) {

    ResText(R.string.material_theme_2_1)

    ResText(R.string.material_theme_2_2)

    CodeListing(
        code = """
            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)

                setContent {
                    c-AppTheme {
                        c-AppLayout()
                    }
                }
            }
        """.trimIndent()
    )
}

private val material_theme_3 = LessonPage (
    headingResId = R.string.material_theme_3_heading
) {

    ResText(R.string.material_theme_3_1)

    ResText(R.string.material_theme_3_2)

    CodeListing(
        code = """        
            c-Card(
                onClick = { isSelected = !isSelected },
                colors = CardDefaults.c-cardColors(
                    containerColor = if (isSelected) {
                        MaterialTheme.c-colorScheme.primaryContainer
                    } else MaterialTheme.c-colorScheme.surfaceContainer,
                    contentColor = if (isSelected) {
                        MaterialTheme.c-colorScheme.onPrimaryContainer
                    } else MaterialTheme.c-colorScheme.primary
                )
            ) { 
                c-Text("Material 3 card")
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        var isSelected by remember { mutableStateOf(false) }

        Card(
            onClick = { isSelected = !isSelected },
            modifier = Modifier
                .height(80.dp)
                .widthIn(max = 300.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = if (isSelected) {
                    MaterialTheme.colorScheme.primaryContainer
                } else MaterialTheme.colorScheme.surfaceContainer,
                contentColor = if (isSelected) {
                    MaterialTheme.colorScheme.onPrimaryContainer
                } else MaterialTheme.colorScheme.primary
            )
        ) {
            Text(
                text = "Material 3 card",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

private val material_theme_4 = LessonPage (
    headingResId = R.string.material_theme_4_heading
) {

    ResText(R.string.material_theme_4_1)

    CodeListing(
        code = """        
            val typography = Typography(
                headlineSmall = TextStyle(
                   fontWeight = FontWeight.SemiBold,
                   fontSize = 24.sp,
                   lineHeight = 32.sp,
                   letterSpacing = 0.sp
                ),
                titleLarge = TextStyle(
                   fontWeight = FontWeight.Normal,
                   fontSize = 18.sp,
                   lineHeight = 28.sp,
                   letterSpacing = 0.sp
                ),
                // ...
            )
        """.trimIndent()
    )

    CodeListing(
        code = """        
            c-MaterialTheme(
               colorScheme = colors,
               typography = typography,
               content = content
            )
        """.trimIndent()
    )
}

private val material_theme_5 = LessonPage (
    headingResId = R.string.material_theme_5_heading
) {

    ResText(R.string.material_theme_5_1)

    CodeListing(
        code = """
            c-Text(
                text = "Large headline",
                style = MaterialTheme.c-typography.headlineLarge
            )

            c-Text(
                text = "Medium body",
                style = MaterialTheme.c-typography.bodyMedium
            )

            c-Text(
                text = "Small label",
                style = MaterialTheme.c-typography.labelSmall
            )
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(
                text = "Large headline",
                style = MaterialTheme.typography.headlineLarge
            )

            Text(
                text = "Medium body",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Small label",
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

private val material_theme_6 = LessonPage (
    headingResId = R.string.material_theme_6_heading
) {

    ResText(R.string.material_theme_6_1)

    CodeListing(
        code = """
            val shapes = Shapes(
               extraSmall = RoundedCornerShape(4.dp),
               small = RoundedCornerShape(8.dp),
               medium = RoundedCornerShape(16.dp),
               large = RoundedCornerShape(24.dp),
               extraLarge = RoundedCornerShape(32.dp)
            )
        """.trimIndent()
    )

    CodeListing(
        code = """        
            c-MaterialTheme(
               colorScheme = colors,
               typography = typography,
               shapes = shapes,
               content = content
            )
        """.trimIndent()
    )
}

private val material_theme_7 = LessonPage (
    headingResId = R.string.material_theme_7_heading
) {

    ResText(R.string.material_theme_7_1)

    CodeListing(
        code = """
            c-Button(
                shape = MaterialTheme.c-shapes.large,
                // ...
            ) { //... }

            c-Button(
                shape = MaterialTheme.c-shapes.medium,
                // ...
            ) { //... }

            c-Button(
                shape = MaterialTheme.c-shapes.small,
                // ...
            ) { //... }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Button(
                shape = MaterialTheme.shapes.large,
                onClick = {}
            ) {
                Text("Large")
            }

            Button(
                shape = MaterialTheme.shapes.medium,
                onClick = {}
            ) {
                Text("Medium")
            }

            Button(
                shape = MaterialTheme.shapes.small,
                onClick = {}
            ) {
                Text("Small")
            }
        }
    }
}

private val material_theme_8 = LessonPage (
    headingResId = R.string.material_theme_8_heading
) {

    ResText(R.string.material_theme_8_1)

    CodeListing(
        code = """
            c-Card { //... }

            c-MaterialTheme(
                shapes = MaterialTheme.c-shapes.copy(
                    medium = CutCornerShape(10.dp)
                ),
                colorScheme = MaterialTheme.c-colorScheme.copy(
                    surfaceVariant = c-colorResource(R.color.teal_700)
                )
            ) {
                c-Card { // ... }
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Card(
                modifier = Modifier
                    .height(80.dp)
                    .width(150.dp)
            ) {
                Text(
                    text = "Default",
                    modifier = Modifier.padding(16.dp)
                )
            }

            MaterialTheme(
                shapes = MaterialTheme.shapes.copy(
                    medium = CutCornerShape(10.dp)
                ),
                colorScheme = MaterialTheme.colorScheme.copy(
                    surfaceVariant = colorResource(R.color.teal_700)
                )
            ) {
                Card(
                    modifier = Modifier
                        .height(80.dp)
                        .width(150.dp)
                ) {
                    Text(
                        text = "Custom",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

val materialThemePages = listOf(
    material_theme_1,
    material_theme_2,
    material_theme_3,
    material_theme_4,
    material_theme_5,
    material_theme_6,
    material_theme_7,
    material_theme_8
)