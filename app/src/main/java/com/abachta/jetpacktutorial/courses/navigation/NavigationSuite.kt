package com.abachta.jetpacktutorial.courses.navigation

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.ui.components.CodeListing
import com.abachta.jetpacktutorial.ui.components.Preview

private val nav_suite_1 = LessonPage (
//   headingResId = R.string.nav_suite_1_heading
) {

    CodeListing(
        code = """
            
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
//
//        var currentDestination by remember {
//            mutableStateOf(topLevelRoutes.first())
//        }
//
//        var currentLayoutIndex by remember { mutableIntStateOf(0) }
//        val layouts = remember {
//            listOf(
//                NavigationSuiteType.NavigationBar to "NavigationBar",
//                NavigationSuiteType.NavigationDrawer to "NavigationDrawer",
//                NavigationSuiteType.NavigationRail to "NavigationRail",
//                NavigationSuiteType.None to "None"
//            )
//        }
//
//        val (layout, label) = layouts[currentLayoutIndex]
//
//        NavigationSuiteScaffold(
//            navigationSuiteItems = {
//                topLevelRoutes.forEach { route ->
//                    item(
//                        icon = {
//                            Icon(
//                                imageVector = route.selectedIcon,
//                                contentDescription = null
//                            )
//                        },
//                        label = { Text(route.name) },
//                        selected = route == currentDestination,
//                        onClick = { currentDestination = route },
//                    )
//                }
//            },
//            layoutType = layout,
//            navigationSuiteColors = NavigationSuiteDefaults.colors(
//                navigationBarContainerColor = MaterialTheme.colorScheme.primaryContainer,
//                navigationDrawerContainerColor = MaterialTheme.colorScheme.primaryContainer,
//                navigationRailContainerColor =  MaterialTheme.colorScheme.primaryContainer,
//            ),
//            modifier = Modifier.height(300.dp)
//        ) {
//
//            Column(
//                modifier = Modifier.fillMaxSize(),
//                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//
//                val text = stringResource(R.string.nav_suite_1_current, label)
//                Text(text)
//
//                Button(onClick = {
//                    currentLayoutIndex = (currentLayoutIndex + 1) % 4
//                }) {
//                    ResText(R.string.nav_suite_1_change)
//                }
//            }
//        }
    }
}

val navigationSuitePages = listOf(
    nav_suite_1
)