package com.abachta.jetpacktutorial.courses.getting_started

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.ui.components.CodeListing
import com.abachta.jetpacktutorial.ui.components.LabeledImage
import com.abachta.jetpacktutorial.ui.components.ResText

private val creating_first_1 = LessonPage (
    headingResId = R.string.creating_first_1_heading
) {

    ResText(R.string.creating_first_1_1)

    CodeListing(
        code = """
            // ...
            import androidx.compose.material3.Text
            
            class MainActivity : ComponentActivity() {
                override fun onCreate(savedInstanceState: Bundle?) {
                    super.onCreate(savedInstanceState)
            
                    setContent {
                        c-Text("Hello, World!")
                    }
                }
            }
        """.trimIndent()
    )

    ResText(R.string.creating_first_1_2)
}

private val creating_first_2 = LessonPage (
    headingResId = R.string.creating_first_2_heading
) {

    ResText(R.string.creating_first_2_1)

    CodeListing(
        code = """
            @Composable
            fun MyComposable(name: String) {
                c-Text("Hello, ${'$'}name!")
            }
        """.trimIndent()
    )

    ResText(R.string.creating_first_2_2)
}

private val creating_first_3 = LessonPage (
    headingResId = R.string.creating_first_3_heading
) {

    ResText(R.string.creating_first_3_1)

    CodeListing(
        code = """
            @Preview
            @Composable
            fun MyComposablePreview() {
                c-MyComposable("Android")
            }
        """.trimIndent()
    )

    ResText(R.string.creating_first_3_2)

    LabeledImage(
        imageResId = R.drawable.android_studio_preview_buttons,
        labelTextId = R.string.creating_first_3_label_1,
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .width(250.dp)
    )

    LabeledImage(
        imageResId = R.drawable.android_studio_preview_split,
        labelTextId = R.string.creating_first_3_label_2,
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .fillMaxWidth()
    )
}

private val creating_first_4 = LessonPage (
    headingResId = R.string.creating_first_4_heading
) {

    ResText(R.string.creating_first_4_1)

    CodeListing(
        code = """
            setContent {
                c-MyComposable("Android")
            }
        """.trimIndent()
    )

    ResText(R.string.creating_first_4_2)

    CodeListing(
        code = """
            setContent {
                c-FirstContainer {
                    c-SomeText()
                    c-AnotherContainer {
                        c-MyComposable("Android")
                    }
                }
            }
        """.trimIndent()
    )
}

val creatingFirstComposablePages = listOf(
    creating_first_1,
    creating_first_2,
    creating_first_3,
    creating_first_4
)
