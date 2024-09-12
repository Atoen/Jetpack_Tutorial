package com.abachta.jetpacktutorial.data

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

interface LessonPage {
    @Composable
    fun Content()
}

sealed class Lesson(
    val title: String,
    val pages: List<LessonPage>,
    val progress: LessonProgress = LessonProgress(pages.count())
) {
    data object GettingStarted1 : Lesson(
        title = "Getting started 1",
        pages = listOf(GettingStarted1_1, GettingStarted1_2),
    )

    data object GettingStarted2 : Lesson(
        title = "Getting started 2",
        pages = listOf(),
    )

    data object GettingStarted3 : Lesson(
        title = "Getting started 3",
        pages = listOf(),
    )
}

data object GettingStarted1_1 : LessonPage {
    @Composable
    override fun Content() {
        Text("Hello 1")
    }
}

data object GettingStarted1_2 : LessonPage {
    @Composable
    override fun Content() {
        Text("Hello 2")
    }
}





