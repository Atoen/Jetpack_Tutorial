package com.abachta.jetpacktutorial.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.abachta.jetpacktutorial.data.Lesson
import com.abachta.jetpacktutorial.lessons.gettingStartedLessons

@Composable
fun LessonList(
    lessons: List<Lesson>,
    onLessonClick: (Lesson) -> Unit
) {
    LazyColumn {
        items(lessons) { lesson ->
            LessonCard(
                lesson = lesson,
                onClick = { onLessonClick(lesson) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LessonListPreview() {

    LessonList(
        lessons = gettingStartedLessons,
        onLessonClick = {}
    )
}
