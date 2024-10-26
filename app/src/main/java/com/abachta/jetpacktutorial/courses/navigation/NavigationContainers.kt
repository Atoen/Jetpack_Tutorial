package com.abachta.jetpacktutorial.courses.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.ui.components.CodeListing
import com.abachta.jetpacktutorial.ui.components.Preview
import com.abachta.jetpacktutorial.ui.components.ResText
import kotlinx.coroutines.launch

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


private val nav_containers_1 = LessonPage (
    headingResId = R.string.nav_containers_1_heading
) {

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        val navController = rememberNavController()
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

private val nav_containers_2 = LessonPage (
    headingResId = R.string.nav_containers_2_heading
) {

    CodeListing(
        code = """
            c-Scaffold(
                bottomBar = {
                    c-NavigationBar {
                        var selectedIndex by c-rememberSaveable {
                            mutableIntStateOf(0)
                        }

                        topLevelRoutes.forEachIndexed { index, item ->
                            val isSelected = selectedIndex == index
                            c-NavigationBarItem(...)
                        }
                    }
                }
            ) { contentPadding ->
                c-NavHost(...) { ... }
            }
        """.trimIndent()
    )
}

private val nav_containers_3 = LessonPage (
    headingResId = R.string.nav_containers_3_heading
) {

    CodeListing(
        code = """
            c-NavigationBarItem(
                ...
                label = { c-Text(item.name) },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.c-colors(
                    ...
                )
            )
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        val navController = rememberNavController()
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
                            alwaysShowLabel = true,
                            colors = NavigationBarItemDefaults.colors(
                                selectedTextColor = MaterialTheme.colorScheme.primary,
                                selectedIconColor = MaterialTheme.colorScheme.tertiary,
                                unselectedTextColor = MaterialTheme.colorScheme.error
                            ),
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
            modifier = Modifier.height(200.dp)
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

@Composable
private fun DrawerItem(
    text: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
    ) {
        Text(text)

        ResText(R.string.nav_drawer_text)

        Button(onClick = onClick) {
            ResText(R.string.nav_drawer_text_2)
        }
    }
}

private val nav_containers_4 = LessonPage (
    headingResId = R.string.nav_containers_4_heading
) {

    CodeListing(
        code = """
            c-ModalNavigationDrawer(
                drawerContent = {
                    c-ModalDrawerSheet {
                        c-NavigationDrawerItem(...)
                    }
                }
            ) {
                c-NavHost(...) {
                    // screen content
                }
            }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        val navController = rememberNavController()
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    Text(
                        text = "Drawer title",
                        modifier = Modifier.padding(16.dp)
                    )

                    HorizontalDivider(modifier = Modifier.padding(8.dp))

                    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }
                    topLevelRoutes.forEachIndexed { index, item ->
                        val isSelected = selectedIndex == index
                        NavigationDrawerItem(
                            selected = isSelected,
                            onClick = {
                                selectedIndex = index
                                navController.navigate(item.route)
                                scope.launch {
                                    drawerState.close()
                                }
                            },
                            label = { Text(item.name) },
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
            modifier = Modifier.height(300.dp)
        ) {
            NavHost(
                navController = navController,
                startDestination = HomeScreen,
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                composable<HomeScreen> {
                    DrawerItem(
                         text = "Home Screen",
                         onClick = {
                             scope.launch {
                                 drawerState.open()
                             }
                         }
                    )
                }
                composable<SettingsScreen> {
                    DrawerItem(
                        text = "Settings Screen",
                        onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }
                    )
                }
                composable<ProfileScreen> {
                    DrawerItem(
                        text = "Profile Screen",
                        onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }
                    )
                }
            }
        }
    }
}

private val nav_containers_5 = LessonPage (
    headingResId = R.string.nav_containers_5_heading
) {

    CodeListing(
        code = """
            val drawerState = c-rememberDrawerState(DrawerValue.Closed)
            val scope = c-rememberCoroutineScope()
            
            c-ModalNavigationDrawer(
                drawerState = drawerState,
                gesturesEnabled = false
            ) {
                c-NavHost(...) {
                    // screen content
                }
            }
            
            c-Button(
                onClick = {
                    scope.launch {
                        drawerState.open()
                    }
                }
            ) { ... }
        """.trimIndent()
    )
}

private val nav_containers_6 = LessonPage (
    headingResId = R.string.nav_containers_6_heading
) {

    CodeListing(
        code = """
            c-ModalNavigationDrawer(
                ...,
                scrimColor = ...,
                drawerContent = {
                    c-ModalDrawerSheet(
                        drawerContainerColor = ...,
                        drawerContentColor = ...,
                        modifier = Modifier.fillMaxWidth(0.7f)
                    ) { 
                        c-NavigationDrawerItem(...)
                    }
                }
            ) { ... }
        """.trimIndent()
    )

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        val navController = rememberNavController()
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()

        ModalNavigationDrawer(
            drawerState = drawerState,
            scrimColor = Color.Green.copy(alpha = 0.3f),
            drawerContent = {
                ModalDrawerSheet(
                    drawerContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    drawerContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.fillMaxWidth(0.7f)
                ) {
                    Text(
                        text = "Drawer title",
                        modifier = Modifier.padding(16.dp)
                    )

                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.padding(8.dp)
                    )

                    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }
                    topLevelRoutes.forEachIndexed { index, item ->
                        val isSelected = selectedIndex == index
                        NavigationDrawerItem(
                            colors = NavigationDrawerItemDefaults.colors(
                                selectedContainerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                unselectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                                selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                                selectedTextColor = MaterialTheme.colorScheme.onPrimary
                            ),
                            selected = isSelected,
                            onClick = {
                                selectedIndex = index
                                navController.navigate(item.route)
                                scope.launch {
                                    drawerState.close()
                                }
                            },
                            label = { Text(item.name) },
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
            modifier = Modifier.height(300.dp)
        ) {
            NavHost(
                navController = navController,
                startDestination = HomeScreen,
                modifier = Modifier.fillMaxSize()
            ) {
                composable<HomeScreen> {
                    DrawerItem(
                        text = "Home Screen",
                        onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }
                    )
                }
                composable<SettingsScreen> {
                    DrawerItem(
                        text = "Settings Screen",
                        onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }
                    )
                }
                composable<ProfileScreen> {
                    DrawerItem(
                        text = "Profile Screen",
                        onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }
                    )
                }
            }
        }
    }
}

private val nav_containers_7 = LessonPage (
    headingResId = R.string.nav_containers_7_heading
) {

    CodeListing(
        code = """
            c-ModalNavigationDrawer(
                ...,
                drawerContent = {
                    c-ModalDrawerSheet{
                        val navBackStackEntry by navController.c-currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination
                        
                        topLevelRoutes.forEach{ item ->
                            val isSelected = currentDestination?.hierarchy?.any {
                                it.hasRoute(item.route::class)
                            } == true
                        
                            c-NavigationDrawerItem(...)
                        }
                    }
                }
            ) { ... }
        """.trimIndent()
    )

    CodeListing(
        code = """
            c-NavigationDrawerItem(
                ...,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        
                        launchSingleTop = true
                        restoreState = true
                    }  
                }
            )
        """.trimIndent()
    )
}

val navigationContainersPages = listOf(
    nav_containers_1,
    nav_containers_2,
    nav_containers_3,
    nav_containers_4,
    nav_containers_5,
    nav_containers_6,
    nav_containers_7
)