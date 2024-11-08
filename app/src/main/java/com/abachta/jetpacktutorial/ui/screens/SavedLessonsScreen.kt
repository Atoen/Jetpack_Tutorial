package com.abachta.jetpacktutorial.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Lesson
import com.abachta.jetpacktutorial.ui.components.LessonCard
import com.abachta.jetpacktutorial.ui.components.LessonPopupActions

@Composable
fun SavedLessonsScreen(
    savedLessons: List<Lesson>,
    onLessonClicked: (Lesson) -> Unit,
    onRemoveLesson: (Lesson) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        if (savedLessons.isEmpty()) {
            Text(
                text = stringResource(R.string.no_saved_lessons),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        } else {

            val actions = remember {
                LessonPopupActions.SavedLessonsScreen(
                    onUnbookmarkLesson = onRemoveLesson
                )
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(
                    items = savedLessons,
                    key = { it.id.value }
                ) {
                    LessonCard(
                        lesson = it,
                        onClick = { onLessonClicked(it) },
                        popupActions = actions,
                        modifier = Modifier.animateItem()
                    )
                }
            }
        }
    }
}