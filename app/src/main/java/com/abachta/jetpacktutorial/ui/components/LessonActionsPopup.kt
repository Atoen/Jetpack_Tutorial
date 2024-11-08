package com.abachta.jetpacktutorial.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.BookmarkRemove
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.CodeChallenge
import com.abachta.jetpacktutorial.data.Lesson
import com.abachta.jetpacktutorial.data.Quiz

sealed class LessonPopupActions {

    data class CourseScreen(
        val onGoToQuiz: (Quiz) -> Unit,
        val onGoToChallenge: (CodeChallenge) -> Unit,
        val onBookmarkLesson: (Lesson) -> Unit,
        val onUnbookmarkLesson: (Lesson) -> Unit
    ) : LessonPopupActions()

    data class SavedLessonsScreen(
        val onUnbookmarkLesson: (Lesson) -> Unit
    ) : LessonPopupActions()
}

@Composable
fun LessonActionsPopup(
    lesson: Lesson,
    actions: LessonPopupActions
) {
    var showPopup by remember { mutableStateOf(false) }

    IconButton(
        onClick = { showPopup = true },
        modifier = Modifier.size(24.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = null
        )

        MaterialTheme(
            shapes = MaterialTheme.shapes.copy(
                extraSmall = RoundedCornerShape(16.dp)
            )
        ) {
            DropdownMenu(
                expanded = showPopup,
                onDismissRequest = { showPopup = false }
            ) {
                DropdownActions(
                    lesson = lesson,
                    actions = actions,
                    onItemClicked = {
                        showPopup = false
                    }
                )
            }
        }
    }
}

@Composable
private fun DropdownActions(
    lesson: Lesson,
    actions: LessonPopupActions,
    onItemClicked: () -> Unit
) {
    when (actions) {
        is LessonPopupActions.CourseScreen -> {
            lesson.quiz?.let {
                DropdownMenuItem(
                    text = { Text(stringResource(R.string.lesson_more_quiz)) },
                    onClick = {
                        onItemClicked()
                        actions.onGoToQuiz(it)
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Lightbulb,
                            contentDescription = null
                        )
                    }
                )
            }

            lesson.challenge?.let {
                DropdownMenuItem(
                    text = { Text(stringResource(R.string.lesson_more_challenge)) },
                    onClick = {
                        onItemClicked()
                        actions.onGoToChallenge(it)
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Code,
                            contentDescription = null
                        )
                    }
                )
            }

            DropdownMenuItem(
                text = {
                    val text = if (lesson.isBookmarked) {
                        stringResource(R.string.unbookmark_lesson)
                    } else stringResource(R.string.bookmark_lesson)

                    Text(text)
                },
                onClick = {
                    onItemClicked()
                    if (lesson.isBookmarked) {
                        actions.onUnbookmarkLesson(lesson)
                    } else actions.onBookmarkLesson(lesson)
                },
                leadingIcon = {
                    val icon = if (lesson.isBookmarked) {
                        Icons.Filled.BookmarkRemove
                    } else Icons.Filled.BookmarkAdd

                    Icon(
                        imageVector = icon,
                        contentDescription = null
                    )
                }
            )
        }

        is LessonPopupActions.SavedLessonsScreen -> {
            DropdownMenuItem(
                text = { Text(stringResource(R.string.unbookmark_lesson)) },
                onClick = {
                    onItemClicked()
                    actions.onUnbookmarkLesson(lesson)
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.BookmarkRemove,
                        contentDescription = null
                    )
                }
            )
        }
    }
}