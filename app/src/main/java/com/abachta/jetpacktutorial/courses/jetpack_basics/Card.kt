package com.abachta.jetpacktutorial.courses.jetpack_basics

import androidx.compose.foundation.BorderStroke
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
import com.abachta.jetpacktutorial.ui.components.ResText

private val card_1 = LessonPage (
    headingResId = R.string.card_1_heading
) {

    ResText(R.string.card_1_1)

    ResText(R.string.card_1_2)

    CodeListing(
        code = """
            @Composable
            fun SimpleCard() {
                c-Card(modifier = Modifier.padding(16.dp)) {
                    c-Text(
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

    ResText(R.string.card_1_3)

    CodeListing(
        code = """
            @Composable
            fun ClickableCard() {
                var count by c-remember { mutableIntStateOf(0) }
                
                c-Card(onClick = { count++ }) {
                    c-Text(
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

    ResText(R.string.card_2_1)

    CodeListing(
        code = """
            @Composable
            fun SimpleElevatedCard() {
                c-ElevatedCard(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 10.dp
                    )
                ) {
                    c-Text(
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
        code = """
            @Composable
            fun SimpleOutlinedCard() {
                c-OutlinedCard {
                    c-Text(
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

    ResText(R.string.card_3_1)

    CodeListing(
        code = """
            @Composable
            fun CustomCard() {
                c-Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Green,
                        contentColor = Color.DarkGray
                    ),
                    shape = RoundedCornerShape(4.dp),
                    border = BorderStroke(width = 10.dp, color = Color.Red)
                ) {
                    c-Text(
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
            shape = RoundedCornerShape(4.dp),
            border = BorderStroke(width = 10.dp, color = Color.Red)
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