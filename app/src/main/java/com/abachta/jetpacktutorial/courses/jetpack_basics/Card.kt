package com.abachta.jetpacktutorial.courses.jetpack_basics

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.ui.components.CodeListing
import com.abachta.jetpacktutorial.ui.components.Preview

private val card_1 = LessonPage (
    headingResId = R.string.card_1_heading
) {

    CodeListing(
        options = it,
        code = """
            @Composable
            fun SimpleCard() {
                Card(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Default card",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Card {
            Text(
                text = "Default card",
                modifier = Modifier.padding(16.dp)
            )
        }
    }

    CodeListing(
        options = it,
        code = """
            @Composable
            fun ClickableCard() {
                var count by remember { mutableIntStateOf(0) }
                
                Card(onClick = { count++ }) {
                    Text(
                        text = "Count: ${'$'}count",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {

        var count by remember { mutableIntStateOf(0) }

        Card(onClick = { count++ }) {
            Text(
                text = "Count: $count",
                modifier = Modifier.padding(16.dp)
            )
        }
    }

}

private val card_2 = LessonPage (
    headingResId = R.string.card_2_heading
) {

    CodeListing(
        options = it,
        code = """
            @Composable
            fun SimpleElevatedCard() {
                ElevatedCard(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 10.dp
                    )
                ) {
                    Text(
                        text = "Elevated card",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            )
        ) {
            Text(
                text = "Elevated card",
                modifier = Modifier.padding(16.dp)
            )
        }
    }

    CodeListing(
        options = it,
        code = """
            @Composable
            fun SimpleOutlinedCard() {
                OutlinedCard {
                    Text(
                        text = "Outlined card",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        OutlinedCard {
            Text(
                text = "Outlined card",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

private val card_3 = LessonPage (
    headingResId = R.string.card_3_heading
) {

    CodeListing(
        options = it,
        code = """
            @Composable
            fun CustomCard() {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Green,
                        contentColor = Color.DarkGray
                    ),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = "Customized card",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.Green,
                contentColor = Color.DarkGray
            ),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(
                text = "Customized card",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

val cardPages = listOf(
    card_1,
    card_2,
    card_3
)