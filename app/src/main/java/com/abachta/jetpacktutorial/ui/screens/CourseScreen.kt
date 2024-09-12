package com.abachta.jetpacktutorial.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.abachta.jetpacktutorial.data.Lesson
import com.abachta.jetpacktutorial.ui.components.LessonList

@Composable
fun CourseScreen(
    course: Screen.Course,
    onLessonClick: (Lesson) -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(course.titleResId),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(course.descriptionResId),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        LessonList(
            lessons = listOf(
                Lesson.GettingStarted1,
                Lesson.GettingStarted2,
                Lesson.GettingStarted3
            ),
            onLessonClick = onLessonClick
        )
    }
}
