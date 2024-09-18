package com.abachta.jetpacktutorial.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.abachta.jetpacktutorial.data.Course
import com.abachta.jetpacktutorial.data.Lesson
import com.abachta.jetpacktutorial.data.courses
import com.abachta.jetpacktutorial.data.getCourseById
import com.abachta.jetpacktutorial.ui.components.ContinuePopup
import com.abachta.jetpacktutorial.ui.components.CourseList

@Composable
fun HomeScreen(
    courses: List<Course>,
    onCourseClick: (Course) -> Unit,
    lessonToContinue: Lesson? = null,
    onContinueClick: (Course, Lesson) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        lessonToContinue?.let { lesson ->
            ContinuePopup(
                lessonToContinue = lesson,
                onContinueClick = {
                    val course = getCourseById(lesson.id)
                    onContinueClick(course, lesson)
                }
            )
        }

        CourseList(
            courses = courses,
            onCourseClick = onCourseClick
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun HomeScreenPreview() {
    HomeScreen(
        courses = courses,
        onCourseClick = {},
        onContinueClick = { _, _ -> }
    )
}