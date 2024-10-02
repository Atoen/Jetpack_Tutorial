package com.abachta.jetpacktutorial.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.abachta.jetpacktutorial.courses.getLessonById
import com.abachta.jetpacktutorial.data.Lesson
import com.abachta.jetpacktutorial.data.LessonId
import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.data.Quiz
import com.abachta.jetpacktutorial.ui.Screen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LessonScreen(
    lessonData: Screen.Lesson,
    onBack: () -> Unit,
    onLessonComplete: (Lesson) -> Unit,
    onGoToCodeChallenge: () -> Unit,
    onGoToQuiz: (Quiz) -> Unit
) {
    val lesson = getLessonById(LessonId(lessonData.id))
    val lessonPageCount = lesson.pages.count()

    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { lessonPageCount + 1 })

    var showPageIndicator by remember { mutableStateOf(true) }

    LaunchedEffect(pagerState.currentPage) {
        showPageIndicator = true
        delay(2000L)
        showPageIndicator = false
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            if (page < lessonPageCount) {
                Page(lesson.pages[page])
            } else {
                onLessonComplete(lesson)

                LessonFinishedScreen(
                    lesson = lesson,
                    onCompleteClick = { onBack() },
                    onQuizClick = {
                        lesson.quiz?.let {
                            onGoToQuiz(it)
                        }
                    },
                    onCodeChallengeClick = { onGoToCodeChallenge() }
                )
            }
        }

        AnimatedVisibility(
            visible = showPageIndicator,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.8f))
                    .padding(8.dp)
            ) {
                repeat(pagerState.pageCount - 1) { i ->
                    val isOnCurrentPage = pagerState.currentPage == i
                    val color = if (isOnCurrentPage) {
                        MaterialTheme.colorScheme.onSurface
                    } else MaterialTheme.colorScheme.outlineVariant

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

                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Star",
                    tint = if (pagerState.currentPage == lessonPageCount) {
                        MaterialTheme.colorScheme.onSurface
                    } else MaterialTheme.colorScheme.outlineVariant,
                    modifier = Modifier
                        .padding(0.dp)
                        .size(12.dp)
                        .clickable {
                            scope.launch {
                                pagerState.animateScrollToPage(lessonPageCount)
                            }
                        }
                )
            }
        }
    }
}

@Composable
private fun Page(
    page: LessonPage
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        page.headingResId?.let {
            Text(
                style = MaterialTheme.typography.headlineSmall,
                text = stringResource(it)
            )
        }

        page.content(this)

        Spacer(Modifier.height(72.dp))
    }
}

@Composable
private fun LessonFinishedScreen(
    lesson: Lesson,
    onCompleteClick: () -> Unit,
    onQuizClick: () -> Unit,
    onCodeChallengeClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterVertically
        ),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {

        if (lesson.hasChallenge) {
            LessonOptionCard(
                text = "Code challenge",
                icon = Icons.Filled.Code,
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                onClick = onCodeChallengeClick
            )
        }

        lesson.quiz?.let {
            LessonOptionCard(
                text = "Start quiz",
                icon = Icons.Filled.Lightbulb,
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                onClick = onQuizClick
            )
        }

        LessonOptionCard(
            text = "Lesson completed",
            icon = Icons.Filled.Check,
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            onClick = onCompleteClick
        )
    }
}

@Composable
private fun LessonOptionCard(
    text: String,
    icon: ImageVector,
    containerColor: Color,
    onClick: () -> Unit,
) {
    ElevatedCard(
        onClick = onClick,
        colors = CardDefaults.elevatedCardColors(
            containerColor = containerColor
        ),
        modifier = Modifier
            .widthIn(max = 500.dp)
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.headlineSmall,
            )

            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}