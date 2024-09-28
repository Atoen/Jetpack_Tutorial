package com.abachta.jetpacktutorial.ui.screens

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.courses.getLessonById
import com.abachta.jetpacktutorial.data.Lesson
import com.abachta.jetpacktutorial.data.LessonId
import com.abachta.jetpacktutorial.data.LessonPageOptions
import com.abachta.jetpacktutorial.settings.AppTheme
import com.abachta.jetpacktutorial.settings.CodeListingFont
import com.abachta.jetpacktutorial.ui.Screen
import com.abachta.jetpacktutorial.ui.components.ExtendableFloatingActionButton
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LessonScreen(
    lessonData: Screen.Lesson,
    onLessonCompleted: (Lesson) -> Unit,
    appTheme: AppTheme,
    listingFont: CodeListingFont
) {
    val lesson = getLessonById(LessonId(lessonData.id))
    val lessonPageCount = lesson.pages.count()

    val lessonPageOptions = remember(appTheme, listingFont) {
        LessonPageOptions(appTheme, listingFont)
    }

    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { lessonPageCount })

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
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                val lessonPage = lesson.pages[page]

                lessonPage.headingResId?.let {
                    Text(
                        style = MaterialTheme.typography.headlineSmall,
                        text = stringResource(it)
                    )
                }

                lessonPage.content(this, lessonPageOptions)

                Spacer(Modifier.height(72.dp))
            }
        }

        val isOnLastPage = pagerState.currentPage ==  lessonPageCount - 1
        val shouldExtend = pagerState.settledPage == lessonPageCount - 1

        ExtendableFloatingActionButton(
            text = { Text(stringResource(R.string.lesson_completed)) },
            extended = shouldExtend,
            icon = {
                Icon(
                    imageVector = if (isOnLastPage) {
                        Icons.Filled.Check
                    } else Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null
                )
            },
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
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        )

        val configuration = LocalConfiguration.current
        val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT

        val indicatorPadding by animateDpAsState(
            if (shouldExtend && isPortrait) 72.dp else 16.dp,
            label = "page_indicator_bottom_padding"
        )

        AnimatedVisibility(
            visible = showPageIndicator,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = indicatorPadding)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(8.dp)
            ) {
                repeat(pagerState.pageCount) { i ->
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
            }
        }
    }
}
