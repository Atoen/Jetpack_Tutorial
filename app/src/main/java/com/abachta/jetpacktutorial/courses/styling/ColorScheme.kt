package com.abachta.jetpacktutorial.courses.styling

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.ui.components.CodeListing
import com.abachta.jetpacktutorial.ui.components.Preview
import com.abachta.jetpacktutorial.ui.components.ResText

private val color_scheme_1 = LessonPage (
    headingResId = R.string.color_scheme_1_heading
) {

    ResText(R.string.color_scheme_1_1)

    ResText(R.string.color_scheme_1_2)

    CodeListing(
        code = """
            <?xml version="1.0" encoding="utf-8"?>
            <resources>
                <color name="purple_200">#FFBB86FC</color>
                <color name="purple_500">#FF6200EE</color>
                <color name="purple_700">#FF3700B3</color>
                <color name="teal_200">#FF03DAC5</color>
                <color name="teal_700">#FF018786</color>
                <color name="black">#FF000000</color>
                <color name="white">#FFFFFFFF</color>
            </resources>
        """.trimIndent()
    )

    ResText(R.string.color_scheme_1_3)

    CodeListing(
        code = """
            val color = c-colorResource(R.color.purple_200)
        """.trimIndent()
    )
}

private val color_scheme_2 = LessonPage (
    headingResId = R.string.color_scheme_2_heading
) {

    ResText(R.string.color_scheme_2_1)

    CodeListing(
        code = """
            c-Card(
                colors = CardDefaults.c-cardColors(
                    containerColor = c-colorResource(R.color.purple_200),
                    contentColor = c-colorResource(R.color.purple_700)
                )
            ) {
                c-Text("Colored card")
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = colorResource(R.color.purple_200),
                contentColor = colorResource(R.color.purple_700)
            )
        ) {
            Text(
                text = "Colored card",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

private val color_scheme_3 = LessonPage (
    headingResId = R.string.color_scheme_3_heading
) {

    ResText(R.string.color_scheme_3_1)

    CodeListing(
        code = """
            c-Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(c-colorResource(R.color.purple_200))
            ) {
                c-Text(
                    text = "Colored box",
                    color = c-colorResource(R.color.purple_700)
                )
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(colorResource(R.color.purple_200))
                .height(100.dp)
        ) {
            Text(
                text = "Colored box",
                color = colorResource(R.color.purple_700),
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

val colorSchemePages = listOf(
    color_scheme_1,
    color_scheme_2,
    color_scheme_3
)