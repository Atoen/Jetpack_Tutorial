package com.abachta.jetpacktutorial.courses.state_lifecycle

import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.ui.components.CodeListing

private val viewmodel_1 = LessonPage (
//   headingResId = R.string.viewmodel_1_heading
) {

    CodeListing(
        code = """
            dependencies {
                // ...
                implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.6")
            }

        """.trimIndent()
    )

    CodeListing(
        code = """
        import androidx.lifecycle.ViewModel
        
        class AppViewModel : ViewModel() {
        }
        """.trimIndent()
    )

    CodeListing(
        code = """
        import androidx.lifecycle.ViewModel
        
        class AppViewModel : ViewModel() {
        }
        """.trimIndent()
    )
}

val viewModelPages = listOf(
    viewmodel_1
)