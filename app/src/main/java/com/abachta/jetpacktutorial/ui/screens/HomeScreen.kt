package com.abachta.jetpacktutorial.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.abachta.jetpacktutorial.data.Course
import com.abachta.jetpacktutorial.data.Lesson
import com.abachta.jetpacktutorial.data.courses
import com.abachta.jetpacktutorial.data.getCourseById
import com.abachta.jetpacktutorial.ui.components.LessonPopup
import com.abachta.jetpacktutorial.ui.components.CourseList
import com.abachta.jetpacktutorial.ui.components.LessonPopupData

@Composable
fun HomeScreen(
    courses: List<Course>,
    onCourseClick: (Course) -> Unit,
    lessonPopupData: LessonPopupData? = null,
    onContinueClick: (Course, Lesson) -> Unit,
    onRefreshPopup: () -> Unit
) {

    LaunchedEffect(true) {
        onRefreshPopup()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        lessonPopupData?.let { data ->
            LessonPopup(
                lessonPopupData = data,
                onContinueClick = {
                    val course = getCourseById(data.lesson.courseId.value)
                    onContinueClick(course, data.lesson)
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
        onContinueClick = { _, _ -> },
        onRefreshPopup = {}
    )
}