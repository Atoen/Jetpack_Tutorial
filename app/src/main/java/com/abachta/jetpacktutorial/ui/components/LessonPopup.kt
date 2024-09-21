package com.abachta.jetpacktutorial.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Lesson
import com.abachta.jetpacktutorial.data.getCourseById
import kotlinx.coroutines.delay

enum class LessonPopupType {
    Start,
    Continue
}

data class LessonPopupData(val lesson: Lesson, val type: LessonPopupType)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonPopup(
    lessonPopupData: LessonPopupData,
    onContinueClick: () -> Unit
) {
    var dismissed by rememberSaveable { mutableStateOf(false) }
    var animated by rememberSaveable { mutableStateOf(false) }
    var display by remember { mutableStateOf(animated) }

    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            if (it != SwipeToDismissBoxValue.Settled) {
                dismissed = true
            }

            true
        }
    )

    if (!animated) {
        LaunchedEffect(true) {
            delay(200)
            display = true
            animated = true
        }
    }

    AnimatedVisibility(
        visible = display && !dismissed,
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        SwipeToDismissBox(
            state = dismissState,
            backgroundContent = {
                val alignment = when (dismissState.dismissDirection) {
                    SwipeToDismissBoxValue.StartToEnd -> TextAlign.Start
                    SwipeToDismissBoxValue.EndToStart -> TextAlign.End
                    else -> TextAlign.Center
                }

                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = stringResource(R.string.swipe_to_dismiss),
                        textAlign = alignment,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.outlineVariant,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center)
                            .padding(16.dp),
                    )
                }
            }
        ) {
            Surface(
                shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
                color = MaterialTheme.colorScheme.primaryContainer,
                onClick = onContinueClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {

                        val topTextId = when (lessonPopupData.type) {
                            LessonPopupType.Start -> R.string.popup_start
                            LessonPopupType.Continue -> R.string.popup_continue
                        }

                        Text(
                            text = stringResource(topTextId),
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )

                        val lesson = lessonPopupData.lesson
                        val course = getCourseById(lesson.courseId.value)

                        Text(
                            text = stringResource(course.titleResId),
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )

                        Text(
                            text = stringResource(lesson.titleResId),
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}