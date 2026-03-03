package com.kaiganews.app.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Radio
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Radio
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Categories : Screen("categories")
    object CategoryDetail : Screen("category/{categoryName}") {
        fun createRoute(categoryName: String) = "category/$categoryName"
    }
    object Search : Screen("search")
    object Settings : Screen("settings")
    object Radio : Screen("radio")
    object ArticleDetail : Screen("article/{articleId}") {
        fun createRoute(articleId: String) = "article/$articleId"
    }
}

data class BottomNavItem(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

val bottomNavItems = listOf(
    BottomNavItem(
        route = Screen.Home.route,
        title = "Home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    ),
    BottomNavItem(
        route = Screen.Radio.route,
        title = "Radio",
        selectedIcon = Icons.Filled.Radio,
        unselectedIcon = Icons.Outlined.Radio
    ),
    BottomNavItem(
        route = Screen.Categories.route,
        title = "Categories",
        selectedIcon = Icons.Filled.Category,
        unselectedIcon = Icons.Outlined.Category
    ),
    BottomNavItem(
        route = Screen.Search.route,
        title = "Search",
        selectedIcon = Icons.Filled.Search,
        unselectedIcon = Icons.Outlined.Search
    )
)
