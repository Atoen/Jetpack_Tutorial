package com.abachta.jetpacktutorial.courses.state

import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.ui.components.CodeListing

private val remember_1 = LessonPage (
//   headingResId = R.string.remember_1_heading
) {

    CodeListing(
        code = """
            var state = remember { mutableStateOf(0) }
            // val value = state.value
            
            var state by remember
        """.trimIndent()
    )
}