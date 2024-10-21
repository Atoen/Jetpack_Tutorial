package com.abachta.jetpacktutorial.courses.state_lifecycle

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.ui.components.CodeListing
import com.abachta.jetpacktutorial.ui.components.LabeledImage
import com.abachta.jetpacktutorial.ui.components.ResText

private val lifecycle_1 = LessonPage (
    headingResId = R.string.lifecycle_1_heading
) {

    ResText(R.string.lifecycle_1_1)

    ResText(R.string.lifecycle_1_2)

    LabeledImage(
        imageResId = R.drawable.composable_lifecycle,
        labelTextId = R.string.lifecycle_1_label
    )

    ResText(R.string.lifecycle_1_3)
}

private val lifecycle_2 = LessonPage (
    headingResId = R.string.lifecycle_2_heading
) {

    ResText(R.string.lifecycle_2_1)

    ResText(R.string.lifecycle_2_2)
}

private val lifecycle_3 = LessonPage (
    headingResId = R.string.lifecycle_3_heading
) {

    ResText(R.string.lifecycle_3_1)

    CodeListing(
        code = """
            @Composable
            fun UsersScreen(users: List<User>) {
                c-Column {
                    for (user in users) {
                        c-UserCard(user)
                    }
                }
            }
        """.trimIndent()
    )

    ResText(R.string.lifecycle_3_2)

    CodeListing(
        code = """
            @Composable
            fun UserCard(user: User) {
                val avatar = loadNetworkImage(user.avatarUrl)
                c-UserAvatar(avatar)
                // ...
            }
        """.trimIndent()
    )

    ResText(R.string.lifecycle_3_3)
}

private val lifecycle_4 = LessonPage (
    headingResId = R.string.lifecycle_4_heading
) {

    ResText(R.string.lifecycle_4_1)

    CodeListing(
        code = """
            @Composable
            fun UsersScreen(users: List<User>) {
                c-Column {
                    for (user in users) {
                        c-key(user.id) {
                            c-UserCard(user)
                        }
                    }
                }
            }
        """.trimIndent()
    )
}

val lifecyclePages = listOf(
    lifecycle_1,
    lifecycle_2,
    lifecycle_3,
    lifecycle_4
)