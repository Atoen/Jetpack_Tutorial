package com.abachta.jetpacktutorial.courses.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CarRepair
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.ui.components.CodeListing
import com.abachta.jetpacktutorial.ui.components.Preview

private val box_1 = LessonPage (
  headingResId = R.string.box_1_heading
) {

    CodeListing(
        code = """
            c-Box(
                modifier = Modifier.size(200.dp)
            ) {
                c-Text(
                    text = "Top Left",
                    modifier = Modifier.align(Alignment.TopStart)
                )
                c-Text(
                    text = "Center",
                    modifier = Modifier.align(Alignment.Center)
                )
                c-Text(
                    text = "Bottom Right",
                    modifier = Modifier.align(Alignment.BottomEnd)
                )
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Box(
            modifier = Modifier.size(150.dp)
        ) {
            Text(
                text = "Top Left",
                modifier = Modifier.align(Alignment.TopStart)
            )
            Text(
                text = "Center",
                modifier = Modifier.align(Alignment.Center)
            )
            Text(
                text = "Bottom Right",
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
    }
}

private val box_2 = LessonPage (
  headingResId = R.string.box_1_heading
) {

    CodeListing(
        code = """
            c-Box(
                contentAlignment = Alignment.TopEnd,
                modifier = Modifier.size(100.dp)
            ) {
                c-Text("Aligned")
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Box(
            contentAlignment = Alignment.TopEnd,
            modifier = Modifier.size(100.dp)
        ) {
            Text("TopEnd")
        }
    }

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier.size(100.dp)
        ) {
            Text("BottomCenter")
        }
    }
}

private val box_3 = LessonPage (
  headingResId = R.string.box_1_heading
) {

    CodeListing(
        code = """
            c-Box(modifier = Modifier.size(100.dp)) {
                c-Icon(
                    imageVector = Icons.Filled.CarRepair,
                    contentDescription = null,
                    modifier = Modifier.matchParentSize()
                )
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Box(modifier = Modifier.size(100.dp)) {
            Icon(
                imageVector = Icons.Filled.CarRepair,
                contentDescription = null,
                modifier = Modifier.matchParentSize()
            )
        }
    }

}

val boxPages = listOf(
    box_1,
    box_2,
    box_3
)