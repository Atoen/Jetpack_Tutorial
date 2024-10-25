package com.abachta.jetpacktutorial.courses.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.ui.components.CodeListing
import com.abachta.jetpacktutorial.ui.components.Preview

data class TopLevelRoute<T>(
    val name: String,
    val route: T,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)

val topLevelRoutes = listOf(
    TopLevelRoute("Home", HomeScreen, Icons.Filled.Home, Icons.Outlined.Home),
    TopLevelRoute("Profile", ProfileScreen("1"), Icons.Filled.Person, Icons.Outlined.Person),
    TopLevelRoute("Settings", SettingsScreen, Icons.Filled.Settings, Icons.Outlined.Settings)
)


private val bottom_nav_1 = LessonPage (
//   headingResId = R.string.bottom_nav_1_heading
) {

    val navController = rememberNavController()

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Scaffold(
            bottomBar = {
                NavigationBar {
                    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }

                    topLevelRoutes.forEachIndexed { index, item->
                        val isSelected = selectedIndex == index
                        NavigationBarItem(
                            selected = isSelected,
                            onClick = {
                                selectedIndex = index
                                navController.navigate(item.route)
                            },
                            label = { Text(item.name) },
                            alwaysShowLabel = false,
                            icon = {
                                Icon(
                                    imageVector = if (isSelected) {
                                        item.selectedIcon
                                    } else item.unselectedIcon,
                                    contentDescription = item.name
                                )
                            }
                        )
                    }
                }
            },
            modifier = Modifier.height(400.dp)
        ) { contentPadding ->
            NavHost(
                navController = navController,
                startDestination = HomeScreen,
                modifier = Modifier.padding(contentPadding)
            ) {
                composable<HomeScreen> {
                    Text("Home Screen")
                }
                composable<SettingsScreen> {
                    Text("Settings Screen")
                }
                composable<ProfileScreen> {
                    Text("Profile Screen")
                }
            }
        }
    }
}

private val bottom_nav_2 = LessonPage (
//   headingResId = R.string.bottom_nav_2_heading
) {

    CodeListing(
        code = """
                CodeListing(
                    code = ""${'"'}
                    c-Scaffold(
                        bottomBar = {
                            c-NavigationBar {
                                var selectedIndex by c-rememberSaveable {
                                    mutableIntStateOf(0)
                                }

                                topLevelRoutes.forEachIndexed { index, item->
                                    val isSelected = selectedIndex == index
                                    c-NavigationBarItem(...)
                                }
                            }
                        }
                    ) { contentPadding ->
                        c-NavHost(
                            navController = navController,
                            startDestination = HomeScreen,
                            modifier = Modifier.padding(contentPadding)
                        ) {
                            // routes
                        }
                    }
                    ""${'"'}.trimIndent()
                )
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {

    }
}

val navigationContainersPages = listOf(
    bottom_nav_1,
    bottom_nav_2
)