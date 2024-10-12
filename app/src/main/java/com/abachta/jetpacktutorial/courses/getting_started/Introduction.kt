package com.abachta.jetpacktutorial.courses.getting_started

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.ui.components.CodeListing
import com.abachta.jetpacktutorial.ui.components.ItemizationMode
import com.abachta.jetpacktutorial.ui.components.ItemizedList
import com.abachta.jetpacktutorial.ui.components.ListItem.Companion.toTextItem
import com.abachta.jetpacktutorial.ui.components.Preview
import com.abachta.jetpacktutorial.ui.components.ResText

private val introduction_1 = LessonPage(
    headingResId = R.string.introduction_1_heading
) {
    ResText(R.string.introduction_1_1)

    Preview(
        showBorder = false,
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .width(200.dp)
    ) {
        var expanded by remember { mutableStateOf(true) }
        ElevatedCard(
            colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
            onClick = { expanded = !expanded }
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painterResource(R.drawable.jetpack_compose), null)
                AnimatedVisibility(expanded) {
                    Text(
                        text = "Click me",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
            }
        }
    }

    CodeListing(
        code = """
            @Composable
            fun JetpackCompose() {
                var expanded by remember {
                    mutableStateOf(true)
                }
                
                c-Card(onClick = {
                    expanded = !expanded
                }) {
                    c-Image(painterResource(R.drawable.jetpack_compose))
                    c-AnimatedVisibility(expanded) { 
                        Text("Click me")
                    }
                }
            }
        """.trimIndent()
    )
}

private val introduction_2 = LessonPage (
   headingResId = R.string.introduction_2_heading
) {

    ResText(R.string.introduction_2_1)

    ResText(R.string.introduction_2_2)

    CodeListing(
        code = """
            @Composable
            fun Greeting(name: String) {
                Text("Hello, ${'$'}name!")
            }
        """.trimIndent()
    )
}

private val introduction_3 = LessonPage (
    headingResId = R.string.introduction_3_heading
) {

    ResText(R.string.introduction_3_1)

    ItemizedList(
        R.string.introduction_3_2.toTextItem(),
        R.string.introduction_3_3.toTextItem(),
        R.string.introduction_3_4.toTextItem(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        itemizationMode = ItemizationMode.Bullet
    )

    ResText(R.string.introduction_3_5)
}

val introductionPages = listOf(
    introduction_1,
    introduction_2,
    introduction_3
)