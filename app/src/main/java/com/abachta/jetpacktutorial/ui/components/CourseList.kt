package com.abachta.jetpacktutorial.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.abachta.jetpacktutorial.data.Course
import com.abachta.jetpacktutorial.data.Courses

@Composable
fun CourseList(
    courses: List<Course>,
    onCourseClick: (Course) -> Unit
) {
    LazyColumn {
        items(courses) { group ->
            CourseCard(
                course = group,
                onClick = { onCourseClick(group) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GroupListPreview() {

    CourseList(
        courses = Courses,
        onCourseClick = {}
    )
}