package com.abachta.jetpacktutorial.courses.getting_started

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.ui.components.CodeListing
import com.abachta.jetpacktutorial.ui.components.ItemizedList
import com.abachta.jetpacktutorial.ui.components.LabeledImage
import com.abachta.jetpacktutorial.ui.components.ListItem.Companion.toTextItem
import com.abachta.jetpacktutorial.ui.components.ResText

private val setting_up_1 = LessonPage(
    headingResId = R.string.setting_up_1_heading
) {

    ResText(R.string.setting_up_1_1)

    ResText(
        resId = R.string.setting_up_1_2,
        style = MaterialTheme.typography.headlineSmall
    )

    ResText(R.string.setting_up_1_3)

    ItemizedList(
        R.string.setting_up_1_4.toTextItem(),
        R.string.setting_up_1_5.toTextItem(),
        R.string.setting_up_1_6.toTextItem(),
        R.string.setting_up_1_7.toTextItem(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    )
}

private val setting_up_2 = LessonPage (
    headingResId = R.string.setting_up_2_heading
) {

    ResText(R.string.setting_up_2_1)

    CodeListing(
        code = """
            android {
                buildFeatures {
                    compose = true
                }
            }
        """.trimIndent()
    )

    ResText(R.string.setting_up_2_2)

    ResText(R.string.setting_up_2_3)

    CodeListing(
        code = """
            dependencies {
                val composeBom = platform("androidx.compose:compose-bom:2024.08.00")
                implementation(composeBom)
                androidTestImplementation(composeBom)
                
                // Material Design 3
                implementation("androidx.compose.material3:material3")
                
                // Android Studio Preview support
                implementation("androidx.compose.ui:ui-tooling-preview")
                debugImplementation("androidx.compose.ui:ui-tooling")
                
                // Optional - Add full set of material icons
                implementation("androidx.compose.material:material-icons-extended")
            }
        """.trimIndent()
    )
}

private val setting_up_3 = LessonPage (
    headingResId = R.string.setting_up_3_heading
) {

    ResText(R.string.setting_up_3_1)

    ItemizedList(
        R.string.setting_up_3_2.toTextItem(),
        R.string.setting_up_3_3.toTextItem(),
        R.string.setting_up_3_4.toTextItem(),
        R.string.setting_up_3_5.toTextItem(),
        R.string.setting_up_3_6.toTextItem(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    )

    ResText(R.string.setting_up_3_7)
}

private val setting_up_4 = LessonPage (
    headingResId = R.string.setting_up_4_heading
) {

    ResText(R.string.setting_up_4_1)

    ResText(
        resId = R.string.setting_up_4_2,
        style = MaterialTheme.typography.headlineSmall
    )

    ItemizedList(
        R.string.setting_up_4_3.toTextItem(),
        R.string.setting_up_4_4.toTextItem(),
        R.string.setting_up_4_5.toTextItem(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    )

    ResText(
        resId = R.string.setting_up_4_6,
        style = MaterialTheme.typography.headlineSmall
    )

    ResText(R.string.setting_up_4_7)

    ResText(R.string.setting_up_4_8)

    LabeledImage(
        imageResId = R.drawable.emulator_select,
        labelTextId = R.string.setting_up_4_label_1,
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .width(250.dp)
    )

    ItemizedList(
        R.string.setting_up_4_9.toTextItem(),
        R.string.setting_up_4_10.toTextItem(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    )
}

private val setting_up_5 = LessonPage (
    headingResId = R.string.setting_up_5_heading
) {

    ResText(R.string.setting_up_5_1)
}


val settingUpEnvironmentPages = listOf(
    setting_up_1,
    setting_up_2,
    setting_up_3,
    setting_up_4,
    setting_up_5
)