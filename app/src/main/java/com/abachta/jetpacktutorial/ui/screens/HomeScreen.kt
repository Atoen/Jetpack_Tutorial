package com.abachta.jetpacktutorial.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.abachta.jetpacktutorial.ui.components.CourseList
import com.abachta.jetpacktutorial.data.Course
import com.abachta.jetpacktutorial.data.Courses
import kotlinx.serialization.Serializable


@Composable
fun HomeScreen(
    onCourseClick: (Course) -> Unit = {}
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        CourseList(
            courses = Courses,
            onCourseClick = onCourseClick
        )
    }
}