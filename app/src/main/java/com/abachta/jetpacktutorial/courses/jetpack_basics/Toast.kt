package com.abachta.jetpacktutorial.courses.jetpack_basics

import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.ui.components.CodeListing
import com.abachta.jetpacktutorial.ui.components.Preview
import com.abachta.jetpacktutorial.ui.components.ResText

private val toast_1 = LessonPage (
    headingResId = R.string.toast_1_heading
) {

    ResText(R.string.toast_1_1)

    ResText(R.string.toast_1_2)

    CodeListing(
        code = """
            @Composable
            fun Toasts() {
                val context = LocalContext.current
        
                Button(onClick = {
                    Toast.makeText(context, "Toast", Toast.LENGTH_SHORT).show()
                }) {
                    Text("Show short")
                }
        
                Button(onClick = {
                    Toast.makeText(context, "Toast", Toast.LENGTH_LONG).show()
                }) {
                    Text("Show long")
                }
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.horizontalScroll(rememberScrollState())
        ) {
            val context = LocalContext.current

            Button(onClick = {
                Toast.makeText(context, "Short toast", Toast.LENGTH_SHORT).show()
            }) {
                Text("Show short")
            }

            Button(onClick = {
                Toast.makeText(context, "Long toast", Toast.LENGTH_LONG).show()
            }) {
                Text("Show long")
            }
        }
    }
}

private val toast_2 = LessonPage (
    headingResId = R.string.toast_2_heading
) {

    ResText(R.string.toast_2_1)

    CodeListing(
        code = """
            @Composable
            fun ToastCallbacks() {
                val context = LocalContext.current
                var isVisible by remember { mutableStateOf(false) }
                val callback = remember { object : Toast.Callback() {
                    override fun onToastShown() { isVisible = true }
                    override fun onToastHidden() { isVisible = false }
                } }

                Button(onClick = {
                    Toast.makeText(context, "Toast", Toast.LENGTH_SHORT).apply {
                        addCallback(callback)
                        show()
                    }
                }) {
                    Text("Show toast")
                }

                Text(if (isVisible) "visible" else "not visible")
            }
        """.trimIndent()
    )

    val ok = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R
    Preview(
        modifier = Modifier.align(Alignment.CenterHorizontally)
    ) {
        if (ok) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                val context = LocalContext.current
                var isVisible by remember { mutableStateOf(false) }

                val callback = remember { object : Toast.Callback() {
                    override fun onToastShown() {
                        isVisible = true
                    }

                    override fun onToastHidden() {
                        isVisible = false
                    }
                } }

                Button(onClick = {
                    Toast.makeText(context, "Toast", Toast.LENGTH_SHORT).apply {
                        addCallback(callback)
                        show()
                    }
                }) {
                    Text("Show toast")
                }

                Text(if (isVisible) "visible" else "not visible")
            }
        } else {
            Text(
                text = stringResource(R.string.toast_requires_newer_api),
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

private val toast_3 = LessonPage (
    headingResId = R.string.toast_3_heading
) {

    ResText(R.string.toast_3_1)

    CodeListing(
        code = """
            @Composable
            fun CancelToast() {
                val context = LocalContext.current
                var isVisible by remember { mutableStateOf(false) }
                val toast = remember {
                    Toast.makeText(context, "Toast", Toast.LENGTH_LONG)
                }
    
                Button(onClick = {
                    if (isVisible) {
                        toast.cancel()
                    } else {
                        toast.show()
                    }
                    
                    isVisible = !isVisible
                }) {
                    Text(if (isVisible) "Show" else "Cancel")
                }
            }
        """.trimIndent()
    )

    Preview(
        modifier = Modifier.align(Alignment.CenterHorizontally),
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val context = LocalContext.current
            var isVisible by remember { mutableStateOf(false) }
            val toast = remember {
                Toast.makeText(context, "Toast", Toast.LENGTH_LONG)
            }

            Button(onClick = {
                if (isVisible) {
                    toast.cancel()
                } else {
                    toast.show()
                }

                isVisible = !isVisible
            }) {
                Text(if (isVisible) "Cancel" else "Show")
            }
        }
    }
}

val toastPages = listOf(
    toast_1,
    toast_2,
    toast_3
)