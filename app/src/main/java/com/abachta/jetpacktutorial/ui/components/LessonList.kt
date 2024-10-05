package com.abachta.jetpacktutorial.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abachta.jetpacktutorial.data.Lesson
import com.abachta.jetpacktutorial.courses.getting_started.gettingStartedLessons

@Composable
fun LessonList(
    lessons: List<Lesson>,
    onLessonClick: (Lesson) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
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
