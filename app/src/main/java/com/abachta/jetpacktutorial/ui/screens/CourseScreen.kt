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
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.CourseId
import com.abachta.jetpacktutorial.data.Lesson
import com.abachta.jetpacktutorial.data.completedCount
import com.abachta.jetpacktutorial.data.getCourseById
import com.abachta.jetpacktutorial.ui.Screen
import com.abachta.jetpacktutorial.ui.components.LessonList

@Composable
fun CourseScreen(
    courseData: Screen.Course,
    onLessonClick: (Lesson) -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(courseData.titleResId),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = stringResource(courseData.descriptionResId),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        val course = getCourseById(CourseId(courseData.id))
        val lessons = course.lessons

        Text(
            text = stringResource(R.string.completed_n_of, lessons.completedCount(), lessons.count()),
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(4.dp))

        LessonList(
            lessons = course.lessons,
            onLessonClick = onLessonClick
        )
    }
}