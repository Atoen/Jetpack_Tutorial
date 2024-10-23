@file:OptIn(ExperimentalMaterial3Api::class)

package com.abachta.jetpacktutorial.courses.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.ui.components.CodeListing
import com.abachta.jetpacktutorial.ui.components.ResText
import kotlinx.coroutines.launch

private val bottom_sheet_1 = LessonPage (
   headingResId = R.string.bottom_sheet_1_heading
) {

    CodeListing(
        code = """
            var showBottomSheet by c-remember { mutableStateOf(false) }
            val sheetState = c-rememberModalBottomSheetState()
            
            if (showBottomSheet) {
                c-ModalBottomSheet(
                    sheetState = sheetState,
                    onDismissRequest = { showBottomSheet = false }
                ) {
                    // ...
                }
            }
        """.trimIndent()
    )

    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            showBottomSheet = true
        }) {
            ResText(R.string.bottom_sheet_display)
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { showBottomSheet = false }
        ) {
            ResText(
                resId = R.string.bottom_sheet_1_content,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Button(
                onClick = {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            showBottomSheet = false
                        }
                    }
                },
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                ResText(R.string.bottom_sheet_hide)
            }
        }
    }

    CodeListing(
        code = """
            c-Button(onClick = {
                scope.launch {
                    sheetState.hide()
                }.invokeOnCompletion {
                    if (!sheetState.isVisible) {
                        showBottomSheet = false
                    }
                }
            }) {
                // sheet content
            }
        """.trimIndent()
    )
}

private val bottom_sheet_2 = LessonPage (
   headingResId = R.string.bottom_sheet_2_heading
) {
    CodeListing(
        code = """
            var showBottomSheet by c-remember { mutableStateOf(false) }
            val sheetState = c-rememberModalBottomSheetState(
                skipPartiallyExpanded = false
            )
            
            if (showBottomSheet) {
                c-ModalBottomSheet(
                    modifier = Modifier.fillMaxHeight(),
                    sheetState = sheetState,
                    onDismissRequest = { showBottomSheet = false }
                ) {
                    // sheet content
                }
            }
        """.trimIndent()
    )

    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            showBottomSheet = true
        }) {
            ResText(R.string.bottom_sheet_display)
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            modifier = Modifier.fillMaxHeight(),
            sheetState = sheetState,
            onDismissRequest = { showBottomSheet = false }
        ) {
            ResText(
                resId = R.string.bottom_sheet_2_content,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}

private val bottom_sheet_3 = LessonPage (
   headingResId = R.string.bottom_sheet_3_heading
) {
    CodeListing(
        code = """
            ModalBottomSheet(
                // ...
                sheetMaxWidth = 200.dp,
                dragHandle = {
                    Text(
                        text = "Custom handle",
                        modifier = Modifier.padding(8.dp)
                    )
                }
            ) {
                // sheet content
            }
        """.trimIndent()
    )

    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            showBottomSheet = true
        }) {
            ResText(R.string.bottom_sheet_display)
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { showBottomSheet = false },
            sheetMaxWidth = 200.dp,
            dragHandle = {
                Text(
                    text = "Custom handle",
                    modifier = Modifier.padding(8.dp)
                )
            }
        ) {
            ResText(
                resId = R.string.bottom_sheet_2_content,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}

private val bottom_sheet_4 = LessonPage (
   headingResId = R.string.bottom_sheet_4_heading
) {
    CodeListing(
        code = """
        ModalBottomSheet(
            // ...
            shape = CutCornerShape(
                topStart = 20.dp,
                topEnd = 20.dp
            ),
            contentColor = Color.LightGray,
            containerColor = Color.DarkGray,
            scrimColor = Color.Red.copy(alpha = 0.2f)
        ) {
            // sheet content
        }
        """.trimIndent()
    )

    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            showBottomSheet = true
        }) {
            ResText(R.string.bottom_sheet_display)
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { showBottomSheet = false },
            shape = CutCornerShape(
                topStart = 20.dp,
                topEnd = 20.dp
            ),
            contentColor = Color.LightGray,
            containerColor = Color.DarkGray,
            scrimColor = Color.Red.copy(alpha = 0.2f)
        ) {
            ResText(
                resId = R.string.bottom_sheet_1_content,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}

val bottomSheetPages = listOf(
    bottom_sheet_1,
    bottom_sheet_2,
    bottom_sheet_3,
    bottom_sheet_4
)