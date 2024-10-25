package com.abachta.jetpacktutorial.courses.animations

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandIn
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.WifiTethering
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.ui.components.CodeListing
import com.abachta.jetpacktutorial.ui.components.Preview
import com.abachta.jetpacktutorial.ui.components.ResText

private val animated_visibility_1 = LessonPage (
   headingResId = R.string.animated_visibility_1_heading
) {

    CodeListing(
        code = """
            var show by c-remember { mutableStateOf(true) }
            
            c-Column {
                c-AnimatedVisibility(show) {
                    c-Box { ... }
                }
            }
        """.trimIndent()
    )

    Preview(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally)
    ) {
        Column {

            var show by remember { mutableStateOf(true) }

            AnimatedVisibility(show) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .height(80.dp)
                        .background(MaterialTheme.colorScheme.tertiaryContainer)
                )
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(80.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .clickable { show = !show }
            ) {
                ResText(
                    resId = R.string.animated_visibility_click,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
    }

    Preview(
        modifier = Modifier
            .height(100.dp)
            .align(Alignment.CenterHorizontally)
    ) {
        Row {

            var show by remember { mutableStateOf(true) }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(8.dp)
                    .width(100.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .clickable { show = !show }
            ) {
                ResText(
                    resId = R.string.animated_visibility_click,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        textAlign = TextAlign.Center
                    )
                )
            }

            AnimatedVisibility(show) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(8.dp)
                        .width(100.dp)
                        .background(MaterialTheme.colorScheme.tertiaryContainer)
                )
            }
        }
    }
}

private val animated_visibility_2 = LessonPage (
   headingResId = R.string.animated_visibility_2_heading
) {

    val enterTransitions = remember { listOf(
        fadeIn() to fadeOut() to "fade",
        slideInHorizontally() to slideOutHorizontally() to "slideHorizontally",
        slideInVertically() to slideOutVertically() to "slideVertically",
        scaleIn() to scaleOut() to "scale",
        expandIn() to shrinkOut() to "expandShrink",
        expandHorizontally() to shrinkHorizontally() to "expandShrinkHorizontally",
        expandVertically() to shrinkVertically() to "expandShrinkVertically"
    ) }

    var visible by remember { mutableStateOf(true) }

    Button(
        modifier = Modifier.align(Alignment.CenterHorizontally),
        onClick = { visible = !visible }
    ) {
        ResText(R.string.animated_visibility_click)
    }

    Preview(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            enterTransitions.forEach { (transition, label) ->
                val (enter, exit) = transition

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.height(50.dp).fillMaxWidth()
                ) {
                    Text(
                        text = label,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )
                    AnimatedVisibility(
                        visible = visible,
                        enter = enter,
                        exit = exit,
                    ) {
                        Icon(
                            imageVector = Icons.Filled.WifiTethering,
                            contentDescription = null,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }
            }
        }
    }
}

private val animated_visibility_3 = LessonPage (
   headingResId = R.string.animated_visibility_3_heading
) {

    CodeListing(
        code = """
            c-AnimatedVisibility(
                enter = scaleIn() + slideInHorizontally(),
                exit = fadeOut() + slideOutVertically(),
                ...
            ) { ... }
        """.trimIndent()
    )

    Preview(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally)
    ) {
        Column {

            var show by remember { mutableStateOf(true) }

            AnimatedVisibility(
                visible = show,
                enter = scaleIn() + slideInHorizontally(),
                exit = fadeOut() + slideOutVertically()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .height(80.dp)
                        .background(MaterialTheme.colorScheme.tertiaryContainer)
                )
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(80.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .clickable { show = !show }
            ) {
                ResText(
                    resId = R.string.animated_visibility_click,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
    }
}

private val animated_visibility_4 = LessonPage (
   headingResId = R.string.animated_visibility_4_heading
) {

    CodeListing(
        code = """
            c-AnimatedVisibility(visible) {
                c-Text(
                    text = "Card text",
                    modifier = Modifier
                        .animateEnterExit(
                            enter = ...
                            exit = ...
                        )
                )
                
                c-Icon(
                    modifier = Modifier
                        .animateEnterExit(...),
                    ...
                )
        """.trimIndent()
    )

    var visible by remember { mutableStateOf(true) }

    Button(
        modifier = Modifier.align(Alignment.CenterHorizontally),
        onClick = { visible = !visible }
    ) {
        ResText(R.string.animated_visibility_click)
    }

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        AnimatedVisibility(visible) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                modifier = Modifier.fillMaxWidth()
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Card text",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier
                            .padding(16.dp)
                            .animateEnterExit(
                                enter = slideInHorizontally { -it },
                                exit = slideOutHorizontally { -it }
                            )
                    )

                    Icon(
                        imageVector = Icons.Filled.AddCircleOutline,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(16.dp)
                            .size(50.dp)
                            .animateEnterExit(
                                enter = scaleIn(),
                                exit = scaleOut()
                            )
                    )
                }
            }
        }
    }
}

private val animated_visibility_5 = LessonPage (
   headingResId = R.string.animated_visibility_5_heading
) {

    CodeListing(
        code = """
            var count by c-remember { mutableIntStateOf(5) }
            
            c-AnimatedContent(
                targetState = count,
                transitionSpec = {
                    if (targetState > initialState) {
                        fadeIn() togetherWith fadeOut()
                    } else {
                        fadeIn() togetherWith fadeOut() 
                    }.using(
                        SizeTransform(clip = false)
                    )
                }
            ) { targetCount ->
                ...
            }
        """.trimIndent()
    )

    var count by remember { mutableIntStateOf(5) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)
    ) {
        Button(onClick = { count-- }
        ) {
            Text("-")
        }

        Button(onClick = { count++}
        ) {
            Text("+")
        }
    }


    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        AnimatedContent(
            targetState = count,
            transitionSpec = {
                if (targetState > initialState) {
                    slideInHorizontally { it } + fadeIn() togetherWith
                            slideOutHorizontally{ -it } + fadeOut()
                } else {
                    slideInHorizontally { -it } + fadeIn() togetherWith
                            slideOutHorizontally { it } + fadeOut()
                }.using(
                    SizeTransform(clip = false)
                )
            }, label = "animated numbers"
        ) { targetCount ->
            Text(
                text = "$targetCount",
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 50.sp
            )
        }
    }
}

val animatedVisibilityPages = listOf(
    animated_visibility_1,
    animated_visibility_2,
    animated_visibility_3,
    animated_visibility_4,
    animated_visibility_5
)