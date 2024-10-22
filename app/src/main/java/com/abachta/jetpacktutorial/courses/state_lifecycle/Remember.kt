package com.abachta.jetpacktutorial.courses.state_lifecycle

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.ui.components.CodeListing
import com.abachta.jetpacktutorial.ui.components.ItemizationMode
import com.abachta.jetpacktutorial.ui.components.ItemizedList
import com.abachta.jetpacktutorial.ui.components.ListItem.Companion.toTextItem
import com.abachta.jetpacktutorial.ui.components.Preview
import com.abachta.jetpacktutorial.ui.components.ResText

private val remember_1 = LessonPage (
   headingResId = R.string.remember_1_heading
) {

    ResText(R.string.remember_1_1)

    ResText(R.string.remember_1_2)

    CodeListing(
        code = """
            val state = c-remember {
                mutableStateOf(...)
            }
        """.trimIndent()
    )

    CodeListing(
        code = """
            val value by c-remember {
                mutableStateOf(...)
            }
        """.trimIndent()
    )

    CodeListing(
        code = """
            val (value, setValue) = c-remember {
                mutableStateOf(...)
            }
        """.trimIndent()
    )

    ResText(R.string.remember_1_3)

    CodeListing(
        code = """
            import androidx.compose.runtime.getValue
            import androidx.compose.runtime.setValue
        """.trimIndent()
    )
}

private val remember_2 = LessonPage (
   headingResId = R.string.remember_2_heading
) {

    ResText(R.string.remember_2_1)

    CodeListing(
        code = """
            var countState by c-remember {
                mutableIntStateOf(0)
            }
            var countInt = c-remember { 0 }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        var countState by remember { mutableIntStateOf(0) }
        var countInt = remember { 0 }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)
        ) {
            Button(onClick = {
                countState++
            }) {
                Text("countState: $countState")
            }

            Button(onClick = {
                countInt++
            }) {
                Text("CountInt: $countInt")
            }
        }
    }

    ResText(R.string.remember_2_2)
}

private val remember_3 = LessonPage (
   headingResId = R.string.remember_3_heading
) {

    ResText(R.string.remember_3_1)

    ResText(R.string.remember_3_2)

    CodeListing(
        code = """
            @Composable
            fun StatelessComposable(
                value: Int,
                onValueChange: (Int) -> Unit
            ) {
                // ...
            }
        """.trimIndent()
    )

    CodeListing(
        code = """
            @Composable
            fun StatefulContainer() {
                var value by c-remember { mutableStateOf(...) }
            
                c-StatelessComposable(
                    value = value,
                    onValueChange = { value = it }
                )
            }
        """.trimIndent()
    )

    ResText(R.string.remember_3_3)

    ItemizedList(
        R.string.remember_3_4.toTextItem(),
        R.string.remember_3_5.toTextItem(),
        R.string.remember_3_6.toTextItem(),
        itemizationMode = ItemizationMode.Bullet
    )
}

private val remember_4 = LessonPage (
   headingResId = R.string.remember_4_heading
) {

    ResText(R.string.remember_4_1)

    CodeListing(
        code = """
            var state by c-rememberSaveable {
                mutableStateOf(...)
            }
        """.trimIndent()
    )

    ResText(R.string.remember_4_2)

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        var value by remember { mutableStateOf("") }
        var saveableValue by rememberSaveable { mutableStateOf("") }

        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            TextField(
                value = value,
                onValueChange = { value = it },
                placeholder = { Text("remember") }
            )

            TextField(
                value = saveableValue,
                onValueChange = { saveableValue = it },
                placeholder = { Text("rememberSaveable") }
            )
        }
    }

    ResText(R.string.remember_4_3)

    CodeListing(
        code = """
            @Parcelize
            data class User(val name: String, val age: Int) : Parcelable
        """.trimIndent()
    )

    CodeListing(
        code = """
            val UserSaver = run {
                val nameKey = "Name"
                val ageKey = "Age"
                mapSaver(
                    save = { mapOf(nameKey to it.name, ageKey to it.age) },
                    restore = { User(it[nameKey] as String, it[ageKey] as Int) }
                )
            }
            
            @Composable
            fun UserCard() {
                var currentUser by c-rememberSaveable(stateSaver = UserSaver) {
                    mutableStateOf(User("Admin", 25))
                }
            }
        """.trimIndent()
    )
}

private val remember_5 = LessonPage (
   headingResId = R.string.remember_5_heading
) {

    ResText(R.string.remember_5_1)

    CodeListing(
        code = """
            val state by c-remember(key1, ...) {
                mutableStateOf(...)
            }            
        """.trimIndent()
    )

    CodeListing(
        code = """
            val state by c-rememberSaveable(key1, ...) {
                mutableStateOf(...)
            }            
        """.trimIndent()
    )
}

val rememberPages = listOf(
    remember_1,
    remember_2,
    remember_3,
    remember_4,
    remember_5
)