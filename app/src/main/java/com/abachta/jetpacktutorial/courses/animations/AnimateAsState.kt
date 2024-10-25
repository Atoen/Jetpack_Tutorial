package com.abachta.jetpacktutorial.courses.animations

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.ui.components.CodeListing
import com.abachta.jetpacktutorial.ui.components.Preview
import com.abachta.jetpacktutorial.ui.components.ResText

private val animating_values_1 = LessonPage (
   headingResId = R.string.animating_values_1_heading
) {

    CodeListing(
        code = """
            var enabled by c-remember { mutableStateOf(false) }
            
            val value by c-animateFloatAsState(
                if (enabled) 1f else 0f
            )
            
            c-Text("Value: ${'$'}value")
            
            val alpha by c-animateFloatAsState(
                if (enabled) 1f else 0.2f
            )
            
            c-Box(modifier = Modifier
                .graphicsLayer(alpha = alpha)
                .background(Color.Red)
            ) 
        """.trimIndent()
    )

    var enabled by remember { mutableStateOf(false) }

    Button(
        modifier = Modifier.align(Alignment.CenterHorizontally),
        onClick = { enabled = !enabled }
    ) {
        ResText(R.string.animation_toggle)
    }

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val value by animateFloatAsState(
                targetValue = if (enabled) 1f else 0f,
                label = "value_animation"
            )

            Text("Value: ${"%.3f".format(value)}")

            val alpha by animateFloatAsState(
                targetValue =  if (enabled) 1f else 0.2f,
                label = "alpha"
            )

            Box(
                modifier = Modifier
                    .size(80.dp)
                    .graphicsLayer(alpha = alpha)
                    .background(Color.Red)
            )
        }
    }
}

private val animating_values_2 = LessonPage (
   headingResId = R.string.animating_values_2_heading
) {

    CodeListing(
        code = """            
            val padding by c-animateDpAsState(
                if (enabled) 30.dp else 4.dp
            )
            
            val size by c-animateDpAsState(
                if (enabled) 80.dp else 30.dp
            )

            c-Text(
                modifier = Modifier.padding(
                    horizontal = 10.dp,
                    vertical = padding
                ),
                ...
            )
            
            c-Icon(
                modifier = Modifier.size(size),
                ...
            )

        """.trimIndent()
    )

    var enabled by remember { mutableStateOf(false) }

    Button(
        modifier = Modifier.align(Alignment.CenterHorizontally),
        onClick = { enabled = !enabled }
    ) {
        ResText(R.string.animation_toggle)
    }

    val padding by animateDpAsState(
        targetValue =  if (enabled) 30.dp else 4.dp,
        label = "padding"
    )

    val size by animateDpAsState(
        targetValue =  if (enabled) 80.dp else 30.dp,
        label = "size"
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Card {
            Row {
                Text(
                    text = "Animated padding",
                    modifier = Modifier.padding(
                        horizontal = 10.dp,
                        vertical = padding
                    )
                )

                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null,
                    modifier = Modifier.size(size)
                )
            }
        }
    }
}

private val animating_values_3 = LessonPage (
   headingResId = R.string.animating_values_3_heading
) {

    CodeListing(
        code = """
            val padding by c-animateDpAsState(
                targetValue = if (enabled) 30.dp else 4.dp,
                animationSpec = tween(
                    durationMillis = 500,
                    easing = LinearOutSlowInEasing
                )
            )

            val size by c-animateDpAsState(
                targetValue = if (enabled) 80.dp else 30.dp,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
        """.trimIndent()
    )

    var enabled by remember { mutableStateOf(false) }

    Button(
        modifier = Modifier.align(Alignment.CenterHorizontally),
        onClick = { enabled = !enabled }
    ) {
        ResText(R.string.animation_toggle)
    }

    val padding by animateDpAsState(
        targetValue = if (enabled) 30.dp else 4.dp,
        animationSpec = tween(
            durationMillis = 500,
            easing = LinearOutSlowInEasing
        ),
        label = "padding"
    )

    val size by animateDpAsState(
        targetValue = if (enabled) 80.dp else 30.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "size"
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Card {
            Row {
                Text(
                    text = "Animated padding",
                    modifier = Modifier.padding(
                        horizontal = 10.dp,
                        vertical = padding
                    )
                )

                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null,
                    modifier = Modifier.size(size)
                )
            }
        }
    }
}

enum class BoxState {
    Collapsed,
    Expanded
}

private val animating_values_4 = LessonPage (
   headingResId = R.string.animating_values_4_heading
) {

    CodeListing(
        code = """
            enum class BoxState {
                Collapsed,
                Expanded
            }
        """.trimIndent()
    )

    CodeListing(
        code = """
            var currentState by c-remember {
                mutableStateOf(BoxState.Collapsed)
            }
            val transition = c-updateTransition(currentState)
        """.trimIndent()
    )

    CodeListing(
        code = """
            val color by transition.c-animateColor { state ->
                when (state) {
                    BoxState.Collapsed -> Color.Gray
                    BoxState.Expanded -> Color.Red
                }
            }
            val borderWidth by transition.c-animateDp { state ->
                when (state) {
                    BoxState.Collapsed -> 15.dp
                    BoxState.Expanded -> 1.dp
                }
            }
        """.trimIndent()
    )

    var state by remember { mutableStateOf(BoxState.Collapsed) }

    Button(
        modifier = Modifier.align(Alignment.CenterHorizontally),
        onClick = {
            state = if (state == BoxState.Collapsed) {
                BoxState.Expanded
            } else BoxState.Collapsed
        }
    ) {
        ResText(R.string.animation_toggle)
    }

    val transition = updateTransition(state, label = "box state")

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        val color by transition.animateColor(label = "color") { state ->
            when (state) {
                BoxState.Collapsed -> Color.Gray
                BoxState.Expanded -> Color.Red
            }
        }
        val borderWidth by transition.animateDp(label = "border width") { state ->
            when (state) {
                BoxState.Collapsed -> 15.dp
                BoxState.Expanded -> 1.dp
            }
        }

        Box(
            modifier = Modifier
                .border(borderWidth, Color.Green)
                .background(color)
                .size(80.dp)
        )
    }
}

private val animating_values_5 = LessonPage (
   headingResId = R.string.animating_values_5_heading
) {

    CodeListing(
        code = """
            val infiniteTransition = c-rememberInfiniteTransition(label = "infinite")
            val color by infiniteTransition.c-animateColor(
                initialValue = Color.Red,
                targetValue = Color.Green,
                animationSpec = infiniteRepeatable(
                    animation = tween(1000, easing = LinearEasing),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "color"
            )
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        val infiniteTransition = rememberInfiniteTransition(label = "infinite")
        val color by infiniteTransition.animateColor(
            initialValue = Color.Red,
            targetValue = Color.Green,
            animationSpec = infiniteRepeatable(
                animation = tween(1000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "color"
        )

        Box(
            modifier = Modifier
                .background(color)
                .size(80.dp)
        )
    }
}


val animatingValuesPages = listOf(
    animating_values_1,
    animating_values_2,
    animating_values_3,
    animating_values_4,
    animating_values_5
)