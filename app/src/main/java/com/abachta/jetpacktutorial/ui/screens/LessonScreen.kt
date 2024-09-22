package com.abachta.jetpacktutorial.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Lesson
import com.abachta.jetpacktutorial.data.LessonId
import com.abachta.jetpacktutorial.lessons.getLessonById
import com.abachta.jetpacktutorial.ui.Screen
import com.abachta.jetpacktutorial.ui.components.ExtendableFloatingActionButton
import kotlinx.coroutines.launch

@Composable
fun LessonScreen(
    lessonData: Screen.Lesson,
    onLessonCompleted: (Lesson) -> Unit,
) {
    val lesson = getLessonById(LessonId(lessonData.id))

    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { lesson.pages.count() + 1 })

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            val isOnLastPage = pagerState.currentPage == lesson.pages.count()
            val shouldExtend = pagerState.settledPage == lesson.pages.count()

            ExtendableFloatingActionButton(
                icon = {
                    Icon(
                        imageVector = if (isOnLastPage) {
                            Icons.Filled.Check
                        } else Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null
                    )
                },
                text = { Text(stringResource(R.string.lesson_completed)) },
                onClick = {
                    if (isOnLastPage) {
                        lesson.complete()
                        onLessonCompleted(lesson)
                    } else {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                },
                extended = shouldExtend
            )
        }
    ) { contentPadding ->
        Box(
            modifier = Modifier.padding(contentPadding)
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                if (page == 0) {
                    LessonStartPage(lessonData)
                } else {
                    lesson.pages[page - 1].content()
                }
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                repeat(pagerState.pageCount) { i ->
                    val color = if (pagerState.currentPage == i) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.outlineVariant
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(8.dp)
                            .clickable {
                                scope.launch {
                                    pagerState.animateScrollToPage(i)
                                }
                            }
                    )
                }
            }
        }
    }
}

@Composable
fun LessonStartPage(lesson: Screen.Lesson) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(lesson.titleResId),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(lesson.descriptionResId),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}