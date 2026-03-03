package com.kaiganews.app.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kaiganews.app.data.sync.NewsSyncWorker
import com.kaiganews.app.ui.navigation.Screen
import com.kaiganews.app.ui.navigation.bottomNavItems
import com.kaiganews.app.ui.screens.categories.CategoriesScreen
import com.kaiganews.app.ui.screens.detail.ArticleDetailScreen
import com.kaiganews.app.ui.screens.home.HomeScreen
import com.kaiganews.app.ui.screens.radio.RadioScreen
import com.kaiganews.app.ui.screens.search.SearchScreen
import com.kaiganews.app.ui.screens.settings.SettingsScreen
import com.kaiganews.app.ui.theme.KaigaNewsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        NewsSyncWorker.schedule(this)
        NewsSyncWorker.syncNow(this)
        
        setContent {
            KaigaNewsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainApp()
                }
            }
        }
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    
    val showBottomBar = currentDestination?.route in bottomNavItems.map { it.route }
    
    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    bottomNavItems.forEach { item ->
                        val selected = currentDestination?.hierarchy?.any { it.route == item.route } == true
                        
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
                                    contentDescription = item.title
                                )
                            },
                            label = { Text(item.title) },
                            selected = selected,
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
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    onArticleClick = { articleId ->
                        navController.navigate(Screen.ArticleDetail.createRoute(articleId))
                    }
                )
            }
            
            composable(Screen.Radio.route) {
                RadioScreen()
            }
            
            composable(Screen.Categories.route) {
                CategoriesScreen(
                    onCategoryClick = { categoryName ->
                        navController.navigate(Screen.CategoryDetail.createRoute(categoryName))
                    }
                )
            }
            
            composable(
                route = Screen.CategoryDetail.route,
                arguments = listOf(navArgument("categoryName") { type = NavType.StringType })
            ) { backStackEntry ->
                val categoryName = backStackEntry.arguments?.getString("categoryName") ?: ""
                CategoriesScreen(
                    onCategoryClick = { }
                )
            }
            
            composable(Screen.Search.route) {
                SearchScreen(
                    onArticleClick = { articleId ->
                        navController.navigate(Screen.ArticleDetail.createRoute(articleId))
                    }
                )
            }
            
            composable(Screen.Settings.route) {
                SettingsScreen()
            }
            
            composable(
                route = Screen.ArticleDetail.route,
                arguments = listOf(navArgument("articleId") { type = NavType.StringType })
            ) { backStackEntry ->
                val articleId = backStackEntry.arguments?.getString("articleId") ?: ""
                ArticleDetailScreen(
                    articleId = articleId,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}
