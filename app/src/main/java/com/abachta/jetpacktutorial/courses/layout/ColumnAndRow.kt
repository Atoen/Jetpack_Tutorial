package com.abachta.jetpacktutorial.courses.layout

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.ui.components.CodeListing
import com.abachta.jetpacktutorial.ui.components.Preview
import com.abachta.jetpacktutorial.ui.components.ResText
import kotlinx.coroutines.launch

private val column_row_1 = LessonPage (
  headingResId = R.string.column_row_1_heading
) {

    ResText(R.string.column_row_1_1)

    CodeListing(
        code = """
            @Composable
            fun TwoTexts() {
                c-Text("First text")
                c-Text("Second text")
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Text("First text")
        Text("Second text")
    }

    ResText(R.string.column_row_1_2)

    CodeListing(
        code = """
            @Composable
            fun ColumnText() {
                c-Column { 
                    c-Text("First text")
                    c-Text("Second text")
                }
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Column {
            Text("First text")
            Text("Second text")
        }
    }

    ResText(R.string.column_row_1_3)
}

private val column_row_2 = LessonPage (
  headingResId = R.string.column_row_2_heading
) {

    ResText(R.string.column_row_2_1)

    CodeListing(
        code = """
            c-Row {
                c-Icon(
                    imageVector = Icons.Filled.AddCircle,
                    contentDescription = null
                )
                c-Column {
                    c-Text("First text")
                    c-Text("Second text")
                }
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Row {
            Icon(
                imageVector = Icons.Filled.AddCircle,
                contentDescription = null
            )

            Column {
                Text("First text")
                Text("Second text")
            }
        }
    }

    ResText(R.string.column_row_2_2)

    CodeListing(
        code = """
            c-Column {
                repeat(8) { i -> 
                    c-Text("Text ${'$'}i")
                }
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Column {
            repeat(8) { i ->
                Text("Text $i")
            }
        }
    }
}

private val column_row_3 = LessonPage (
  headingResId = R.string.column_row_3_heading
) {

    ResText(R.string.column_row_3_1)

    CodeListing(
        code = """
            c-Row(verticalAlignment = Alignment.CenterVertically) {
                ...  
                c-Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    ...
                }
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Filled.AddCircle,
                contentDescription = null
            )

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("First text")
                Text("Second text")
            }
        }
    }

    ResText(R.string.column_row_3_2)

    CodeListing(
        code = """
            c-Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ...  
                c-Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    ...
                }
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.AddCircle,
                contentDescription = null
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    space = 16.dp,
                    alignment = Alignment.Top
                )
            ) {
                Text("First text")
                Text("Second text")
            }
        }
    }
}

@Composable
private fun ColumnPreviewCard(text: String) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 2.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 12.dp)
        )
    }
}

private val column_row_4 = LessonPage (
  headingResId = R.string.column_row_4_heading
) {
    val arrangements = remember { listOf(
        "Space Between" to Arrangement.SpaceBetween,
        "Space Around" to Arrangement.SpaceAround,
        "Space Evenly" to Arrangement.SpaceEvenly,
        "Top" to Arrangement.Top,
        "Bottom" to Arrangement.Bottom,
        "Center" to Arrangement.Center
    ) }

    var expanded by remember { mutableStateOf(false) }

    val height by animateDpAsState(
        targetValue = if (expanded) 400.dp else 190.dp,
        label = "column_preview_animation"
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(onClick = { expanded = !expanded }) {
            val text = stringResource(if (expanded) R.string.column_row_collapse else R.string.column_row_expand)
            Text(text)
        }
    }

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally),
            modifier = Modifier.horizontalScroll(rememberScrollState())
        ) {
            arrangements.forEach { (label, arrangement) ->
                Column(
                    modifier = Modifier.width(46.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelSmall,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .height(40.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Column(
                        verticalArrangement = arrangement,
                        modifier = Modifier
                            .height(height)

                    ) {
                        ColumnPreviewCard("A")
                        ColumnPreviewCard("B")
                        ColumnPreviewCard("C")
                    }
                }
            }
        }
    }
}

@Composable
private fun RowPreviewCard(
    text: String,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier.padding(vertical = 2.dp, horizontal = 4.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 20.dp)
        )
    }
}

private val column_row_5 = LessonPage (
  headingResId = R.string.column_row_5_heading
) {
    val arrangements = remember { listOf(
        "Space Between" to Arrangement.SpaceBetween,
        "Space Around" to Arrangement.SpaceAround,
        "Space Evenly" to Arrangement.SpaceEvenly,
        "Start" to Arrangement.Start,
        "End" to Arrangement.End,
        "Center" to Arrangement.Center
    ) }

    var expanded by remember { mutableStateOf(false) }

    val width by animateDpAsState(
        targetValue = if (expanded) 400.dp else 178.dp,
        label = "row_preview_animation"
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(onClick = { expanded = !expanded }) {
            val text = stringResource(if (expanded) R.string.column_row_collapse else R.string.column_row_expand)
            Text(text)
        }
    }

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
            modifier = Modifier.horizontalScroll(rememberScrollState())
        ) {
            arrangements.forEach { (label, arrangement) ->
                Row(
                    modifier = Modifier.height(40.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelSmall,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .width(60.dp)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Row(
                        horizontalArrangement = arrangement,
                        modifier = Modifier.width(width)

                    ) {
                        RowPreviewCard("A")
                        RowPreviewCard("B")
                        RowPreviewCard("C")
                    }
                }
            }
        }
    }
}

private val column_row_6 = LessonPage (
  headingResId = R.string.column_row_6_heading
) {

    ResText(R.string.column_row_6_1)

    CodeListing(
        code = """
            c-Row {
                c-RowCard(
                    text = "A",
                    modifier = Modifier.weight(1f)
                )
                c-RowCard(
                    text = "B",
                    modifier = Modifier.weight(2f)
                )
                c-RowCard(
                    text = "C",
                    modifier = Modifier.weight(3f)
                )
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Row {
            RowPreviewCard("A", modifier = Modifier.weight(1f))
            RowPreviewCard("B", modifier = Modifier.weight(2f))
            RowPreviewCard("C", modifier = Modifier.weight(3f))
        }
    }
}

private val column_row_7 = LessonPage (
  headingResId = R.string.column_row_7_heading
) {

    ResText(R.string.column_row_7_1)

    CodeListing(
        code = """
            c-Row(modifier = Modifier.horizontalScroll(c-rememberScrollState())) {
                repeat(40) { i ->
                    c-Text("Item ${'$'}i")
                }
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
            repeat(40) { i ->
                Text("Item $i")
            }
        }
    }

    CodeListing(
        code = """
            c-Column(modifier = Modifier.verticalScroll(c-rememberScrollState())) {
                repeat(40) { i ->
                    c-Text("Item ${'$'}i")
                }
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Column(
            modifier = Modifier
                .height(200.dp)
                .verticalScroll(rememberScrollState())
        ) {
            repeat(40) { i ->
                Text("Item $i")
            }
        }
    }
}

private val column_row_8 = LessonPage (
  headingResId = R.string.column_row_8_heading
) {

    ResText(R.string.column_row_8_1)

    ResText(R.string.column_row_8_2)

    CodeListing(
        code = """
            c-LazyColumn {
                item {
                    c-Text("First item")
                }
    
                items(1000) { i ->
                    c-Text("Item ${'$'}i")
                }
    
                item {
                    c-Text("Last item")
                }
            }
        """.trimIndent()
    )

    Preview(
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .height(200.dp)
    ) {
        LazyColumn(modifier = Modifier.height(200.dp)) {
            item {
                Text("First item")
            }

            items(1000) { i ->
                Text("Item $i")
            }

            item {
                Text("Last item")
            }
        }
    }
}

private val column_row_9 = LessonPage (
  headingResId = R.string.column_row_9_heading
) {

    ResText(R.string.column_row_9_1)

    CodeListing(
        code = """
            val state = c-rememberLazyListState()
            val scope = c-rememberCoroutineScope()
            
            c-Button(onclick {
                scope.launch {
                    val currentIndex = state.firstVisibleItemIndex
                    state.animateScrollToItem(currentIndex + 1)
                    state.animateScrollBy(-200f)
                }
            }) {
                ...
            }
            
            c-LazyColumn(
                state = state,
                userScrollEnabled = false,
                modifier = Modifier.height(200.dp)
            ) {
                items(1000) { i ->
                    c-Text("Item ${'$'}i")
                }
            }
        """.trimIndent()
    )

    val state = rememberLazyListState()
    val scope = rememberCoroutineScope()

    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally)
    ) {
        Button(onClick = {
            scope.launch {
                val currentIndex = state.firstVisibleItemIndex
                state.animateScrollToItem(currentIndex + 1)
            }
        }) {
            Text("Scroll to next")
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
        LazyColumn(
            state = state,
            userScrollEnabled = false,
            modifier = Modifier.height(200.dp)
        ) {
            items(1000) { i ->
                Text("Item $i")
            }
        }
    }
}

private val column_row_10 = LessonPage (
  headingResId = R.string.column_row_10_heading
) {

    ResText(R.string.column_row_10_1)

    CodeListing(
        code = """            
            c-LazyColumn {
                items(
                    items = itemList,
                    key = { it.id }
                ) { item ->
                    c-ItemCard(
                        item = item,
                        modifier = Modifier.animateItem()
                    )
                }
            }
        """.trimIndent()
    )

    val list = remember { mutableStateListOf(0, 1) }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally)
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
            .height(400.dp)
    ) {
        LazyColumn {
            items(list, key = { it }) { item ->
                Text(
                    text = "Item $item",
                    modifier = Modifier.animateItem()
                )
            }
        }
    }
}

val columnAndRowPages = listOf(
    column_row_1,
    column_row_2,
    column_row_3,
    column_row_4,
    column_row_5,
    column_row_6,
    column_row_7,
    column_row_8,
    column_row_9,
    column_row_10
)