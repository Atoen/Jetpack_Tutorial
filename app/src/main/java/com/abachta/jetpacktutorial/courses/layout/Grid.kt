package com.abachta.jetpacktutorial.courses.layout

import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.ui.components.CodeListing
import com.abachta.jetpacktutorial.ui.components.Preview
import com.abachta.jetpacktutorial.ui.components.ResText
import kotlinx.coroutines.launch

@Composable
private fun GridCellItem(
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
            text = "Item $index",
            modifier = Modifier.padding(4.dp),
        )
    }
}

private val grid_1 = LessonPage (
  headingResId = R.string.grid_1_heading
) {

    ResText(R.string.grid_1_1)

    ResText(R.string.grid_1_2)

    CodeListing(
        code = """
            c-LazyVerticalGrid(
                columns = GridCells.Fixed(3)
            ) {
                items(30) { index ->
                    c-GridItem(index)
                }
            }
        """.trimIndent()
    )

    Preview(
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .height(200.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3)
        ) {
            items(30) { index ->
                GridCellItem(index)
            }
        }
    }
}

private val grid_2 = LessonPage (
  headingResId = R.string.grid_2_heading
) {

    ResText(R.string.grid_2_1)

    CodeListing(
        code = """
            c-LazyVerticalGrid(
                columns = GridCells.FixedSize(60.dp)
                // or Adaptive(minSize = 120.dp)
            ) {
                items(30) { index ->
                    c-GridItem(index)
                }
            }
        """.trimIndent()
    )

    Preview(
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .height(200.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.FixedSize(60.dp)
        ) {
            items(30) { index ->
                GridCellItem(index)
            }
        }
    }

    Preview(
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .height(200.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 120.dp)
        ) {
            items(30) { index ->
                GridCellItem(index)
            }
        }
    }
}

private val grid_3 = LessonPage (
  headingResId = R.string.grid_3_heading
) {

    ResText(R.string.grid_3_1)

    CodeListing(
        code = """
            c-LazyHorizontalGrid(
                rows = GridCells.Adaptive(minSize = 80.dp)
            ) {
                items(30) { index ->
                    c-GridItem(index)
                }
            }
        """.trimIndent()
    )

    Preview(
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .height(200.dp)
    ) {
        LazyHorizontalGrid(
            rows = GridCells.Adaptive(minSize = 80.dp)
        ) {
            items(30) { index ->
                GridCellItem(index)
            }
        }
    }
}

private val grid_4 = LessonPage (
  headingResId = R.string.grid_4_heading
) {

    CodeListing(
        code = """
            c-LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 80.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(30) { index ->
                    c-GridItem(index)
                }
            }
        """.trimIndent()
    )

    Preview(
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .height(200.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 80.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(30) { index ->
                GridCellItem(index)
            }
        }
    }
}

private val grid_5 = LessonPage (
  headingResId = R.string.grid_5_heading
) {

    ResText(R.string.grid_5_1)

    CodeListing(
        code = """
            
            val list = c-remember { mutableStateListOf(...) }
            
            c-LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 80.dp)
            ) {
                items(list, key = { it }) { item ->
                    c-GridItem(
                        index = item,
                        modifier = Modifier
                            .fillMaxSize()
                            .animateItem()
                    )
                }
            }
        """.trimIndent()
    )

    val list = remember { mutableStateListOf(0, 1, 2, 3, 4, 5, 6) }

    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Button(onClick = {
            val count = list.count()
            list.addAll((count..(count + 2)))
        }) {
            Text("Add")
        }

        Button(onClick = { list.clear() }) {
            Text("Clear")
        }

        Button(onClick = { list.shuffle() }) {
            Text("Shuffle")
        }
    }

    Preview(
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .fillMaxWidth()
            .height(300.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 80.dp)
        ) {
            items(list, key = { it }) { item ->
                GridCellItem(
                    index = item,
                    modifier = Modifier
                        .fillMaxSize()
                        .animateItem()
                )
            }
        }
    }
}

private val grid_6 = LessonPage (
  headingResId = R.string.grid_6_heading
) {

    CodeListing(
        code = """
            val state = c-rememberLazyGridState()
            val scope = c-rememberCoroutineScope()
            
            c-Button(onclick {
                scope.launch {
                    val currentIndex = state.firstVisibleItemIndex
                    state.animateScrollToItem(currentIndex + 8)
                    state.animateScrollBy(-200f)
                }
            }) {
                ...
            }
            
            c-LazyVerticalGrid(
                state = state,
                columns = GridCells.Adaptive(minSize = 80.dp),
                userScrollEnabled = false
            ) {
                items(60) { index ->
                    c-GridItem(index)
                }
            }
        """.trimIndent()
    )

    val state = rememberLazyGridState()
    val scope = rememberCoroutineScope()

    Row (
        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally)
    ) {
        Button(onClick = {
            scope.launch {
                val currentIndex = state.firstVisibleItemIndex
                state.animateScrollToItem(currentIndex + 8)
            }
        }) {
            Text("Scroll 8 forward")
        }

        Button(onClick = {
            scope.launch {
                state.animateScrollBy(-200f)
            }
        }) {
            Text("Scroll up by 200px")
        }
    }

    Preview(
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .height(200.dp)
    ) {
        LazyVerticalGrid(
            state = state,
            columns = GridCells.Adaptive(minSize = 80.dp),
            userScrollEnabled = false
        ) {
            items(60) { index ->
                GridCellItem(index)
            }
        }
    }
}

private val sizes = listOf(50, 80, 100, 120, 150).map { it.dp }

@Composable
private fun StaggeredGridCellItem(
    index: Int,
    modifier: Modifier = Modifier
) {
    val i = index % sizes.count()

    val color = when (index % 4) {
        0 -> MaterialTheme.colorScheme.primaryContainer
        2 -> MaterialTheme.colorScheme.tertiaryContainer
        1 -> MaterialTheme.colorScheme.secondaryContainer
        else -> MaterialTheme.colorScheme.surfaceContainer
    }

    Card(
        colors = CardDefaults.cardColors(containerColor = color),
        modifier = modifier
            .fillMaxWidth()
            .height(sizes[i])
            .padding(4.dp)
    ) {
        Text(
            text = "Item $index",
            modifier = Modifier.padding(4.dp),
        )
    }
}

private val grid_7 = LessonPage (
  headingResId = R.string.grid_7_heading
) {

    ResText(R.string.grid_7_1)

    CodeListing(
        code = """
            c-LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Adaptive(140.dp),
                verticalItemSpacing = 4.dp,
            ) {
                items(30) { index ->
                    c-GridItem(index)
                }
            }
        """.trimIndent()
    )

    Preview(
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .height(400.dp)
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Adaptive(140.dp),
            verticalItemSpacing = 4.dp,
        ) {
            items(30) { index ->
                StaggeredGridCellItem(index)
            }
        }
    }
}

val gridPages = listOf(
    grid_1,
    grid_2,
    grid_3,
    grid_4,
    grid_5,
    grid_6,
    grid_7
)