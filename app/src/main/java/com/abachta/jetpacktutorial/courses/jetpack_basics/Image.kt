package com.abachta.jetpacktutorial.courses.jetpack_basics

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.ui.components.CodeListing
import com.abachta.jetpacktutorial.ui.components.Preview
import com.abachta.jetpacktutorial.ui.components.ResText

// https://www.publicdomainpictures.net/en/view-image.php?image=42947&picture=golden-retriever-dog
// https://www.publicdomainpictures.net/en/view-image.php?image=53874&picture=dogs-golden-retriever
// Uicons by <a href="https://www.flaticon.com/uicons">Flaticon</a>

private val image_1 = LessonPage (
   headingResId = R.string.image_1_heading
) {

    ResText(R.string.image_1_1)

    CodeListing(
        code = """
            @Composable
            fun SimpleImage() {
                Image(
                    painter = painterResource(R.drawable.image_dog),
                    contentDescription = null
                )
            }
        """.trimIndent()
    )


    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Image(
            painter = painterResource(R.drawable.image_dog_portrait),
            contentDescription = null
        )
    }
}

private val image_2 = LessonPage (
   headingResId = R.string.image_2_heading
) {

    ResText(R.string.image_2_1)

    CodeListing(
        code = """
            @Composable
            fun VectorImage() {
                Image(
                    painter = painterResource(R.drawable.chart_histogram),
                    contentDescription = null
                )
            }
        """.trimIndent()
    )


    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Image(
            painter = painterResource(R.drawable.chart_histogram),
            contentDescription = null
        )
    }
}


private val image_3 = LessonPage (
   headingResId = R.string.image_3_heading
) {

    ResText(R.string.image_3_1)

    CodeListing(
        code = """
            @Composable
            fun TransparentImage() {
                Image(
                    painter = painterResource(R.drawable.image_dog),
                    contentDescription = null,
                    alpha = alpha
                )
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Column {

            var alpha by remember { mutableFloatStateOf(0.5f) }

            Text("Alpha: $alpha")

            Slider(
                value = alpha,
                onValueChange = { alpha = it }
            )

            Image(
                painter = painterResource(R.drawable.image_dog_portrait),
                contentDescription = null,
                alpha = alpha
            )
        }
    }
}

private val image_4 = LessonPage (
   headingResId = R.string.image_4_heading
) {

    ResText(R.string.image_4_1)

    CodeListing(
        code = """
            @Composable
            fun SaturatedImage() {
                Image(
                    painter = painterResource(R.drawable.image_dog),
                    contentDescription = null,
                    colorFilter = ColorFilter.colorMatrix(
                        ColorMatrix().apply { setToSaturation(saturation) }
                    )
                )
            }
        """.trimIndent()
    )


    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Column {

            var saturation by remember { mutableFloatStateOf(1f) }

            Text("Saturation: $saturation")

            Slider(
                value = saturation,
                onValueChange = { saturation = it },
                valueRange = 0f..2f
            )

            Image(
                painter = painterResource(R.drawable.image_dog_portrait),
                contentDescription = null,
                colorFilter = ColorFilter.colorMatrix(
                    ColorMatrix().apply {
                        setToSaturation(saturation)
                    }
                )
            )
        }
    }
}

private val image_5 = LessonPage (
   headingResId = R.string.image_5_heading
) {

    ResText(R.string.image_5_1)

    CodeListing(
        code = """
            @Composable
            fun TintedImage() {
                Image(
                    painter = painterResource(R.drawable.image_dog),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color.Yellow, blendMode = <blend mode>)
                )
            }
        """.trimIndent()
    )


    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            val blendModes = listOf(
                BlendMode.Clear, BlendMode.Src, BlendMode.Dst, BlendMode.SrcOver, BlendMode.DstOver,
                BlendMode.SrcIn, BlendMode.DstIn, BlendMode.SrcOut, BlendMode.DstOut, BlendMode.SrcAtop,
                BlendMode.DstAtop, BlendMode.Xor, BlendMode.Plus, BlendMode.Modulate, BlendMode.Screen,
                BlendMode.Overlay, BlendMode.Darken, BlendMode.Lighten, BlendMode.ColorDodge, BlendMode.ColorBurn,
                BlendMode.Hardlight, BlendMode.Softlight, BlendMode.Difference, BlendMode.Exclusion,
                BlendMode.Multiply, BlendMode.Hue, BlendMode.Saturation, BlendMode.Color, BlendMode.Luminosity
            )

            // Start on modulate
            var currentBlendModeIndex by remember { mutableIntStateOf(13) }
            val currentBlendMode = blendModes[currentBlendModeIndex]

            Button(onClick = {
                currentBlendModeIndex = (currentBlendModeIndex + 1) % blendModes.size
            }) {
                Text("Blend mode: $currentBlendMode")
            }

            Image(
                painter = painterResource(R.drawable.image_dog_portrait),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.Yellow, blendMode = currentBlendMode)
            )
        }
    }
}

private val image_6 = LessonPage (
   headingResId = R.string.image_6_heading
) {

    ResText(R.string.image_6_1)

    CodeListing(
        code = """
            @Composable
            fun ScaledImage() {
                Image(
                    painter = painterResource(R.drawable.image_dog_portrait),
                    contentDescription = null,
                    contentScale = <content scale>,
                )
            }
        """.trimIndent()
    )

    val imageScalingModes = listOf(
        "Fit" to ContentScale.Fit,
        "Crop" to ContentScale.Crop,
        "FillBounds" to ContentScale.FillBounds,
        "FillHeight" to ContentScale.FillHeight,
        "FillWidth" to ContentScale.FillWidth,
        "Inside" to ContentScale.Inside,
        "None" to ContentScale.None
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            val imageModifier = Modifier
                .size(120.dp)
                .padding(4.dp)
                .border(width = 1.dp, color = Color.Black)
                .background(Color(red = 240, green = 130, blue = 0))

            imageScalingModes.forEach { (label, contentScale) ->
                Text(
                    text =  "Content scale: $label",
                    style = MaterialTheme.typography.labelSmall
                )

                Row {
                    Image(
                        painter = painterResource(R.drawable.image_dog_portrait),
                        contentDescription = null,
                        contentScale = contentScale,
                        modifier = imageModifier
                    )

                    Image(
                        painter = painterResource(R.drawable.image_dog_landscape),
                        contentDescription = null,
                        contentScale = contentScale,
                        modifier = imageModifier
                    )
                }
            }
        }
    }
}

val imagePages = listOf(
    image_1,
    image_2,
    image_3,
    image_4,
    image_5,
    image_6
)