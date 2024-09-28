package com.abachta.jetpacktutorial.courses.jetpack_basics

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.OpenWith
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.ui.components.CodeListing
import com.abachta.jetpacktutorial.ui.components.Preview
import kotlin.math.roundToInt

private val modifier_1 = LessonPage (
   headingResId = R.string.modifier_1_heading
) {

    CodeListing(
        options = it,
        code = """
            @Composable
            fun CardWithModifier() {
                 Card(
                    modifier = Modifier.size(width = 200.dp, height = 120.dp)
                ) {
                    Text(
                        text = "Text card",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Card(
            modifier = Modifier.size(width = 200.dp, height = 120.dp)
        ) {
            Text(
                text = "Text card",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

private val modifier_2 = LessonPage (
   headingResId = R.string.modifier_2_heading
) {

    CodeListing(
        options = it,
        code = """
            @Composable
            fun ModifierOrdering() {
                Text(
                    text = "Text display",
                    modifier = Modifier
                        .padding(16.dp)
                        .background(Color.Green)
                )
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Text(
            text = "Text display",
            modifier = Modifier
                .padding(16.dp)
                .background(Color.Red)
        )
    }

    CodeListing(
        options = it,
        code = """
            @Composable
            fun ModifierOrdering() {
                Text(
                    text = "Text display",
                    modifier = Modifier
                        .background(Color.Red)
                        .padding(16.dp)
                )
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Text(
            text = "Text display",
            modifier = Modifier
                .background(Color.Red)
                .padding(16.dp)
        )
    }
}

private val modifier_3 = LessonPage (
   headingResId = R.string.modifier_3_heading
) {

    CodeListing(
            options = it,
        code = """
            @Composable
            fun Sizing() {
                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Text("Button")
                }
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Text("Button")
        }
    }

    CodeListing(
        options = it,
        code = """
            @Composable
            fun Sizing() {
                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxSize(0.7f)
                        .height(40.dp)
                ) {
                    Text("Button")
                }
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxSize(0.7f)
                .height(40.dp)
        ) {
            Text("Button")
        }
    }
}

private val modifier_4 = LessonPage (
   headingResId = R.string.modifier_4_heading
) {

    CodeListing(
        options = it,
        code = """
            @Composable
            fun PaddedButton() {
                Button(onClick = {}) {
                    Text(
                        text = "Button",
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Button(onClick = {}) {
            Text(
                text = "Button",
                modifier = Modifier.padding(4.dp)
            )
        }
    }

    CodeListing(
        options = it,
        code = """
            @Composable
            fun PaddedButton() {
                Button(onClick = {}) {
                    Text(
                        text = "Button",
                        modifier = Modifier.padding(horizontal = 40.dp, vertical = 10.dp)
                    )
                }
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Button(onClick = {}) {
            Text(
                text = "Button",
                modifier = Modifier.padding(horizontal = 40.dp, vertical = 10.dp)
            )
        }
    }
}

private val modifier_5 = LessonPage (
   headingResId = R.string.modifier_5_heading
) {

    CodeListing(
        options = it,
        code = """
            @Composable
            fun verticalScrollable() {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(stringResource(R.string.lorem_ipsum))
                }
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(stringResource(R.string.lorem_ipsum))
        }
    }

    CodeListing(
        options = it,
        code = """
            @Composable
            fun horizontalScrollable() {
                Box(
                    modifier = Modifier.horizontalScroll(rememberScrollState())
                ) {
                    Text(stringResource(R.string.lorem_ipsum))
                }
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Box(
            modifier = Modifier.horizontalScroll(rememberScrollState())
        ) {
            Text(stringResource(R.string.lorem_ipsum))
        }
    }
}


private val modifier_6 = LessonPage (
   headingResId = R.string.modifier_6_heading
) {

    CodeListing(
        options = it,
        code = """
            @Composable
            fun Clicking() {
                var count by remember { mutableIntStateOf(0) }
                Box(
                    modifier = Modifier
                        .size(width = 120.dp, height = 100.dp)
                        .clickable { count++ }
                ) {
                    Text("Count: ${'$'}count")
                }
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {

        var count by remember { mutableIntStateOf(0) }
        Box(
            modifier = Modifier
                .size(width = 120.dp, height = 60.dp)
                .clickable { count++ }
        ) {
            Text("Count: $count")
        }
    }

    CodeListing(
        options = it,
        code = """
            @Composable
            @OptIn(ExperimentalFoundationApi::class)
            fun CombinedClicking() {
                var count by remember { mutableIntStateOf(0) }
                Box(
                    modifier = Modifier
                        .size(width = 120.dp, height = 60.dp)
                        .combinedClickable(
                            onClick = { count++ },
                            onDoubleClick = { count-- },
                            onLongClick = { count *= 2 }
                        )
                ) {
                    Text("Count: ${'$'}count")
                }
            }
        """.trimIndent()
    )

    @OptIn(ExperimentalFoundationApi::class)
    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {

        var count by remember { mutableIntStateOf(0) }
        Box(
            modifier = Modifier
                .size(width = 120.dp, height = 60.dp)
                .combinedClickable(
                    onClick = { count++ },
                    onDoubleClick = { count-- },
                    onLongClick = { count *= 2  }
                )
        ) {
            Text("Count: $count")
        }
    }
}

private val modifier_7 = LessonPage (
   headingResId = R.string.modifier_7_heading
) {

    CodeListing(
        options = it,
        code = """
            @Composable
            fun HorizontalDrag() {
                var offsetX by remember { mutableStateOf(0f) }
                Text(
                    modifier = Modifier
                        .offset { IntOffset(offsetX.roundToInt(), 0) }
                        .draggable(
                            orientation = Orientation.Horizontal,
                            state = rememberDraggableState { delta ->
                                offsetX += delta
                            }
                        ),
                    text = "<- Drag me ->"
                )
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        var offsetX by remember { mutableFloatStateOf(0f) }
        Text(
            modifier = Modifier
                .offset { IntOffset(offsetX.roundToInt(), 0) }
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { delta ->
                        offsetX += delta
                    }
                ),
            text = "<- Drag me ->"
        )
    }

    CodeListing(
        options = it,
        code = """
            @Composable
            fun DraggableIcon() {
                var offsetX by remember { mutableFloatStateOf(0f) }
                var offsetY by remember { mutableFloatStateOf(0f) }
        
                Icon(
                    imageVector = Icons.Filled.OpenWith,
                    contentDescription = null,
                    Modifier
                        .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                        .pointerInput(Unit) {
                            detectDragGestures { change, dragAmount ->
                                change.consume()
                                offsetX += dragAmount.x
                                offsetY += dragAmount.y
                            }
                        }
                )
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        var offsetX by remember { mutableFloatStateOf(0f) }
        var offsetY by remember { mutableFloatStateOf(0f) }

        Icon(
            imageVector = Icons.Filled.OpenWith,
            contentDescription = null,
            Modifier
                .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        offsetX += dragAmount.x
                        offsetY += dragAmount.y
                    }
                }
        )
    }
}

private val modifier_8 = LessonPage (
   headingResId = R.string.modifier_8_heading
) {

    CodeListing(
        options = it,
        code = """
            @Composable
            fun AnimatedSize() {
                var visible by remember { mutableStateOf(false) }
                
                Button(
                    onClick = { visible = !visible },
                    modifier = Modifier.animateContentSize()
                ) {
                    Text(if (visible) "Visible" else "Hidden")
                    if (visible) {
                        Text("More text")
                    }
                }
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        var short by remember { mutableStateOf(true) }

        Button(
            onClick = { short = !short },
            modifier = Modifier
                .animateContentSize()
                .padding(16.dp)
        ) {
            Text(if (short) "short" else "Longer text that spans\nmultiple lines")
        }
    }
}

private val modifier_9 = LessonPage (
   headingResId = R.string.modifier_9_heading
) {

    CodeListing(
        options = it,
        code = """
            @Composable
            fun BlurredText() {
                Text(
                    text = "Blurred text",
                    modifier = Modifier.blur(2.dp)
                )
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Text(
            text = "Blurred text",
            modifier = Modifier.blur(2.dp)
        )
    }

    CodeListing(
        options = it,
        code = """
            @Composable
            fun BlurredImage() {
                Image(
                    painter = painterResource(R.drawable.image_dog_portrait),
                    contentDescription = null,
                    modifier = Modifier.blur(
                        radius = 20.dp,
                        edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(5.dp))
                    )
                )
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Image(
            painter = painterResource(R.drawable.image_dog_portrait),
            contentDescription = null,
            modifier = Modifier.blur(
                radius = 20.dp,
                edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(5.dp))
            )
        )
    }
}

private val modifier_10 = LessonPage (
   headingResId = R.string.modifier_10_heading
) {

    CodeListing(
        options = it,
        code = """
            @Composable
            fun ClippingShapes() {
                Image(
                    painter = painterResource(R.drawable.image_dog_portrait),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CutCornerShape(topStart = 20.dp, topEnd = 20.dp))
                        .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                )
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Image(
            painter = painterResource(R.drawable.image_dog_portrait),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .clip(CutCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
        )
    }
}

private val modifier_11 = LessonPage (
   headingResId = R.string.modifier_11_heading
) {

    CodeListing(
        options = it,
        code = """
            @Composable
            fun ConditionalModifier() {
                var toggled by remember { mutableStateOf(false) }
                Button(
                    onClick = { toggled = !toggled },
                    modifier = Modifier
                        .fillMaxWidth()
                        .then(if (toggled) Modifier.padding(20.dp) else Modifier)
                        .height(50.dp)
                ) {
                    Text("Toggle modifier")
                }
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        var toggled by remember { mutableStateOf(false) }
        Button(
            onClick = { toggled = !toggled },
            modifier = Modifier
                .fillMaxWidth()
                .then(if (toggled) Modifier.padding(20.dp) else Modifier)
                .height(50.dp)
        ) {
            Text("Toggle modifier")
        }
    }
}


val modifierPages = listOf(
    modifier_1,
    modifier_2,
    modifier_3,
    modifier_4,
    modifier_5,
    modifier_6,
    modifier_7,
    modifier_8,
    modifier_9,
    modifier_10,
    modifier_11,
)