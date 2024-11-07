package com.abachta.jetpacktutorial.courses.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.ui.SnackbarController
import com.abachta.jetpacktutorial.ui.SnackbarEvent
import com.abachta.jetpacktutorial.ui.components.CodeListing
import com.abachta.jetpacktutorial.ui.components.Preview
import com.abachta.jetpacktutorial.ui.components.ResText
import kotlinx.coroutines.launch

@Composable
private fun PagerItem(
    index: Int,
    modifier: Modifier = Modifier
) {
    val color = when (index % 4) {
        0 -> MaterialTheme.colorScheme.primaryContainer
        2 -> MaterialTheme.colorScheme.tertiaryContainer
        1 -> MaterialTheme.colorScheme.secondaryContainer
        else -> MaterialTheme.colorScheme.surfaceContainer
    }

    Card(
        colors = CardDefaults.cardColors(containerColor = color),
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        Text(
            text = "Page $index",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp),
        )
    }
}

private val pager_1 = LessonPage (
  headingResId = R.string.pager_1_heading
) {

    ResText(R.string.pager_1_1)

    CodeListing(
        code = """
            val pagerState = c-rememberPagerState(
                pageCount = { 10 }
            )
            
            // or VerticalPager
            c-HorizontalPager(
                state = pagerState
            ) { page ->
                c-PagerItem(
                    index = page
                )
            }
        """.trimIndent()
    )

    ResText(R.string.pager_1_2)

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        val pagerState = rememberPagerState { 10 }

        HorizontalPager(
            state = pagerState
        ) { page ->
            PagerItem(
                index = page,
                modifier = Modifier.height(80.dp)
            )
        }
    }

    ResText(R.string.pager_1_3)

    Preview(modifier = Modifier
        .align(Alignment.CenterHorizontally)
        .height(160.dp)
    ) {
        val pagerState = rememberPagerState { 10 }

        VerticalPager(
            state = pagerState,
        ) { page ->
            PagerItem(
                index = page
            )
        }
    }
}

private val pager_2 = LessonPage (
   headingResId = R.string.pager_2_heading
) {

    ResText(R.string.pager_2_1)

    ResText(R.string.pager_2_2)

    CodeListing(
        code = """
            c-VerticalPager(
                state = pagerState,
                beyondViewportPageCount = 2
            ) { page ->
                c-PagerItem(
                    index = page
                )
            }
        """.trimIndent()
    )
}

private val pager_3 = LessonPage (
   headingResId = R.string.pager_3_heading
) {

    ResText(R.string.pager_3_1)

    CodeListing(
        code = """
            val scope = c-rememberCoroutineScope()
            val pagerState = c-rememberPagerState(...)
            
            c-Button(onClick = {
                scope.launch {
                    val currentPage = pagerState.currentPage
                    pagerState.animateScrollToPage(
                        currentPage + 1
                    )
                }
            }) { ... }
            
            c-HorizontalPager(
                state = pagerState,
                userScrollEnabled = false
            ) { page ->
                ...
            }
        """.trimIndent()
    )

    val pagerState = rememberPagerState { 10 }
    val scope = rememberCoroutineScope()

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(onClick = {
            scope.launch {
                val currentPage = pagerState.currentPage
                pagerState.animateScrollToPage(currentPage - 1)
            }
        }) {
            ResText(R.string.pager_scroll_backward)
        }

        Button(onClick = {
            scope.launch {
                val currentPage = pagerState.currentPage
                pagerState.animateScrollToPage(currentPage + 1)
            }
        }) {
            ResText(R.string.pager_scroll_forward)
        }
    }

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false
        ) { page ->
            PagerItem(
                index = page,
                modifier = Modifier.height(80.dp)
            )
        }
    }
}

private val pager_4 = LessonPage (
   headingResId = R.string.pager_4_heading
) {

    ResText(R.string.pager_4_1)

    CodeListing(
        code = """
            val pagerState = c-rememberPagerState(...)
            
            c-LaunchedEffect(pagerState) {
                snapshotFlow {
                    pagerState.currentPage
                }.collect { page ->
                    // do something
                }
            }
            
            c-HorizontalPager(
                state = pagerState
            ) { page ->
                ...
            }
        """.trimIndent()
    )

    val pagerState = rememberPagerState { 10 }
    val scope = rememberCoroutineScope()

    val message = stringResource(R.string.pager_current_page_snackbar)

    var sendEvent by remember { mutableStateOf(false) }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->

            if (!sendEvent) {
                sendEvent = true
                return@collect
            }

            scope.launch {
                SnackbarController.sendEvent(SnackbarEvent(
                    message = "$message: $page",
                    duration = SnackbarDuration.Short
                ))
            }
        }
    }

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        HorizontalPager(
            state = pagerState
        ) { page ->
            PagerItem(
                index = page,
                modifier = Modifier.height(80.dp)
            )
        }
    }
}

private val pager_5 = LessonPage (
   headingResId = R.string.pager_5_heading
) {

    ResText(R.string.pager_5_1)

    ResText(R.string.pager_5_2)

    CodeListing(
        code = """
            val pagerState = c-rememberPagerState(...)
            
            c-HorizontalPager(
                state = pagerState,
                userScrollEnabled = false
            ) { page ->
                ...
            }
            
            c-Row(...) {
                repeat(pagerState.pageCount) { i ->
                    val color = ...    
    
                    c-Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(color)
                    )
                }
            }
            
        """.trimIndent()
    )

    val pagerState = rememberPagerState { 10 }

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        HorizontalPager(
            state = pagerState
        ) { page ->
            PagerItem(
                index = page,
                modifier = Modifier.height(80.dp)
            )
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp)
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
                        .size(16.dp)
                )
            }
        }
    }
}

private val twoPagesPerViewport = object : PageSize {
    override fun Density.calculateMainAxisPageSize(
        availableSpace: Int,
        pageSpacing: Int
    ): Int {
        return (availableSpace - 2 * pageSpacing) / 2
    }
}

private val pager_6 = LessonPage (
    headingResId = R.string.pager_6_heading
) {

    ResText(R.string.pager_6_1)

    CodeListing(
        code = """
            c-HorizontalPager(
                pageSize = PageSize.Fixed(100.dp)
            ) { page ->
                ...
            }
 
        """.trimIndent()
    )

    CodeListing(
        code = """
            val twoPagesPerViewport = object : PageSize {
                override fun Density.calculateMainAxisPageSize(
                    availableSpace: Int,
                    pageSpacing: Int
                ): Int {
                    return (availableSpace - 2 * pageSpacing) / 2
                }
            }
        """.trimIndent()
    )


    val pagerState = rememberPagerState { 10 }

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        HorizontalPager(
            state = pagerState,
            pageSize = twoPagesPerViewport
        ) { page ->
            PagerItem(
                index = page,
                modifier = Modifier.height(80.dp)
            )
        }
    }
}

val pagerPages = listOf(
    pager_1,
    pager_2,
    pager_3,
    pager_4,
    pager_5,
    pager_6
)