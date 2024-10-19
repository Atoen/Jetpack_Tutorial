package com.abachta.jetpacktutorial.courses.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.ui.SnackbarAction
import com.abachta.jetpacktutorial.ui.SnackbarController
import com.abachta.jetpacktutorial.ui.SnackbarEvent
import com.abachta.jetpacktutorial.ui.components.CodeListing
import com.abachta.jetpacktutorial.ui.components.Preview
import com.abachta.jetpacktutorial.ui.components.ResText
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
private val scaffold_1 = LessonPage (
   headingResId = R.string.scaffold_1_heading
) {

    ResText(R.string.scaffold_1_1)

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text("Top app bar")
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = {}) {
                    Icon(Icons.Default.Add, null)
                }
            },
            bottomBar = {
                BottomAppBar {
                    Text("Bottom app bar")
                }
            },
            modifier = Modifier.height(400.dp)
        ) { contentPadding ->
            Column (
                modifier = Modifier.padding(contentPadding)
            ) {
                ResText(R.string.scaffold_1_content)
            }
        }
    }
}

private enum class TopBarType {
    None,
    Small,
    CenterAligned,
    Medium,
    Large
}

private enum class BottomBarType {
    None,
    BottomBar,
    BottomBarWithFab
}

private enum class FABType {
    None,
    Standard,
    Small,
    Large,
    Extended
}

private val scaffold_2 = LessonPage (
   headingResId = R.string.scaffold_2_heading
) {

    ResText(R.string.scaffold_2_1)

    var currentTopBarIndex by remember { mutableIntStateOf(1) }
    var currentBottomBarIndex by remember { mutableIntStateOf(1) }
    var currentFABIndex by remember { mutableIntStateOf(1) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(onClick = {
            currentTopBarIndex = (currentTopBarIndex + 1) % TopBarType.entries.size
        }) {
            ResText(R.string.scaffold_2_top_bar)
        }

        Button(onClick = {
            currentBottomBarIndex = (currentBottomBarIndex + 1) % BottomBarType.entries.size
        }) {
            ResText(R.string.scaffold_2_bottom_bar)
        }

        Button(onClick = {
            currentFABIndex = (currentFABIndex + 1) % FABType.entries.size
        }) {
            ResText(R.string.scaffold_2_fab)
        }
    }

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Scaffold(
            topBar = { TopBar(TopBarType.entries[currentTopBarIndex]) },
            bottomBar = {
                BottomBar(
                    barType = BottomBarType.entries[currentBottomBarIndex],
                    fabType = FABType.entries[currentFABIndex]
                )
            },
            floatingActionButton = {
                if (currentBottomBarIndex == 0) {
                    FAB(FABType.entries[currentFABIndex])
                }
            },
            modifier = Modifier.height(400.dp)
        ) { contentPadding ->
            Column (
                modifier = Modifier.padding(contentPadding)
            ) {
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    type: TopBarType,
) {
    when (type) {
        TopBarType.None -> Unit
        TopBarType.Small -> {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Small Top App Bar")
                }
            )
        }
        TopBarType.CenterAligned -> {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Centered Top App Bar",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = null
                        )
                    }
                }
            )
        }
        TopBarType.Medium -> {
            MediumTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Medium Top App Bar",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = null
                        )
                    }
                }
            )
        }
        TopBarType.Large -> {
            LargeTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Large Top App Bar",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    }
}

@Composable
private fun BottomBar(
    barType: BottomBarType,
    fabType: FABType
) {
    when (barType) {
        BottomBarType.None -> Unit
        BottomBarType.BottomBar -> {
            BottomAppBar {
                Text("Bottom App Bar")
            }
        }
        BottomBarType.BottomBarWithFab -> {
            BottomAppBar(
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.Mic,
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.Image,
                            contentDescription = null
                        )
                    }
                },
                floatingActionButton = {
                    FAB(fabType)
                }
            )
        }
    }
}

@Composable
private fun FAB(
    type: FABType
) {
    when (type) {
        FABType.None -> Unit
        FABType.Standard -> {
            FloatingActionButton(
                onClick = {},
            ) {
                Icon(Icons.Filled.Add, null)
            }
        }
        FABType.Small -> {
            SmallFloatingActionButton(
                onClick = {}
            ) {
                Icon(Icons.Filled.Add, null)
            }
        }
        FABType.Large -> {
            LargeFloatingActionButton(
                onClick = {},
                shape = CircleShape,
            ) {
                Icon(Icons.Filled.Add, null)
            }

        }
        FABType.Extended -> {
            ExtendedFloatingActionButton(
                onClick = {},
                icon = { Icon(Icons.Filled.Edit, null) },
                text = { Text(text = "Extended FAB") },
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
private val scaffold_3 = LessonPage (
   headingResId = R.string.scaffold_3_heading
) {

    ResText(R.string.scaffold_3_1)

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.error,

            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text("Top app bar")
                    }
                )
            },
            bottomBar = {
                BottomBar(
                    barType = BottomBarType.BottomBarWithFab,
                    fabType = FABType.Standard
                )
            },
            modifier = Modifier.height(400.dp)
        ) { contentPadding ->
            Column (
                modifier = Modifier.padding(contentPadding)
            ) {
                ResText(R.string.scaffold_1_content)
            }
        }
    }

    ResText(R.string.scaffold_3_2)

    CodeListing(
        code = """
            c-Scaffold(
                // ...
            ) { contentPadding ->
                c-Column ( // or any other composable
                    modifier = Modifier.padding(contentPadding)
                ) {
                    // ...
                }
            }
        """.trimIndent()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
private val scaffold_4 = LessonPage (
   headingResId = R.string.scaffold_4_heading
) {

    ResText(R.string.scaffold_4_1)

    var currentFABPositionIndex by remember { mutableIntStateOf(1) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            currentFABPositionIndex = (currentFABPositionIndex + 1) % 4
        }) {
            ResText(R.string.scaffold_3_fab)
        }
    }

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text("Top app bar")
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = {}) {
                    Icon(Icons.Default.Add, null)
                }
            },
            bottomBar = {
                BottomAppBar {
                    Text("Bottom app bar")
                }
            },
            floatingActionButtonPosition = when (currentFABPositionIndex) {
                0 -> FabPosition.Start
                1 -> FabPosition.Center
                2 -> FabPosition.End
                else -> FabPosition.EndOverlay
            },
            modifier = Modifier.height(400.dp)
        ) { contentPadding ->
            Column (
                modifier = Modifier.padding(contentPadding)
            ) {
            }
        }
    }
}

private val scaffold_5 = LessonPage (
   headingResId = R.string.scaffold_5_heading
) {

    ResText(R.string.scaffold_5_1)

    ResText(R.string.scaffold_5_2)

    val scope = rememberCoroutineScope()
    val innerEvent = SnackbarEvent(
        message = "Dismissible snackbar message!",
        duration = SnackbarDuration.Indefinite,
        dismissible = true
    )

    val outerEvent = SnackbarEvent(
        message = "Hello!",
        duration = SnackbarDuration.Short,
        action = SnackbarAction(
            name = "Click me!",
            action = {
                scope.launch {
                    SnackbarController.sendEvent(innerEvent)
                }
            }
        )
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            scope.launch {
                SnackbarController.sendEvent(outerEvent)
            }
        }) {
            ResText(R.string.scaffold_5_snackbar)
        }
    }

    CodeListing(
        code = """
            val snackbarHostState = c-remember { SnackbarHostState() }
            
            c-Scaffold(
                snackbarHost = {
                    c-SnackbarHost(snackbarHostState)
                }
                // ...
            ) { 
                // ...
            }
        """.trimIndent()
    )

    ResText(R.string.scaffold_5_3)

    CodeListing(
        code = """
            suspend fun showMessage(message: String) {
                val result = snackbarHostState.showSnackbar(
                    message = message,
                    duration = SnackbarDuration.Long, // or Short or Indefinite,
                    withDismissAction = true,
                    actionLabel = "Go"
                )
                
                if (result == SnackbarResult.ActionPerformed) {
                    // ...
                }
            }
        """.trimIndent()
    )

    ResText(R.string.scaffold_5_4)

    CodeListing(
        code = """
            // Not suspending
            snackbarHostState.currentSnackbarData?.dismiss()
        """.trimIndent()
    )
}

val scaffoldPages = listOf(
    scaffold_1,
    scaffold_2,
    scaffold_3,
    scaffold_4,
    scaffold_5
)