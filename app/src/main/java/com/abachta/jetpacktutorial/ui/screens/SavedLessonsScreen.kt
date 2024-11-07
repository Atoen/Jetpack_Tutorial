package com.abachta.jetpacktutorial.ui.screens

import androidx.compose.runtime.Composable
import com.abachta.jetpacktutorial.data.Lesson

@Composable
fun SavedLessonsScreen(
    savedLessons: List<Lesson>,
    onLessonClicked: (Lesson) -> Unit,
    onRemoveLesson: (Lesson) -> Unit
) {

}