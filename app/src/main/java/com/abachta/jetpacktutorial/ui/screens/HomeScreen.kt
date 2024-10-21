package com.abachta.jetpacktutorial.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.abachta.jetpacktutorial.data.Course
import com.abachta.jetpacktutorial.data.Lesson
import com.abachta.jetpacktutorial.data.getCourseById
import com.abachta.jetpacktutorial.ui.components.CourseCard
import com.abachta.jetpacktutorial.ui.components.LessonPopup
import com.abachta.jetpacktutorial.viewmodels.CourseViewModel

@Composable
fun HomeScreen(
    viewModel: CourseViewModel,
    onCourseClick: (Course) -> Unit,
    onContinueClick: (Course, Lesson) -> Unit,
    showPopup: Boolean
) {
    LaunchedEffect(true) {
        viewModel.refreshLessonPopup()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val data = viewModel.lessonPopupData
        if (showPopup && data != null) {
            LessonPopup(
                lessonPopupData = data,
                onContinueClick = {
                    val course = getCourseById(data.lesson.courseId.value)
                    onContinueClick(course, data.lesson)
                },
                shouldAnimate = viewModel.shouldAnimatePopup,
                onAnimated = {
                    viewModel.shouldAnimatePopup = false
                }
            )
        }

        LazyColumn(
            modifier = Modifier.padding(top = 6.dp)
        ) {
            items(viewModel.courses) { group ->
                CourseCard(
                    course = group,
                    onClick = { onCourseClick(group) }
                )
            }
        }
    }
}