package com.example.homepage.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.homepage.ui.components.NavigationPanel
import com.example.homepage.ui.components.TopBar
import com.example.homepage.screens.*

@Composable
fun MainScreen() {
    var currentRoute by remember { mutableStateOf("home") }
    var showMenu by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Top Bar
            TopBar(
                onMenuClick = { showMenu = !showMenu },
                onProfileClick = { currentRoute = "profile" },
                showMenu = showMenu
            )

            // Content Area
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                when (currentRoute) {
                    "home" -> HomeScreen(onNavigate = { currentRoute = it })
                    "trending" -> TrendingScreen(onNavigate = { currentRoute = it })
                    "search" -> SearchScreen()
                    "videos" -> VideosScreen()
                    "community" -> CommunityScreen()
                    "notification" -> NotificationScreen()
                    "profile" -> ProfileScreen(
                        onNavigateToHome = { currentRoute = "home" },
                        onNavigateToSettings = { currentRoute = "settings" }
                    )
                    "settings" -> SettingsScreen(
                        onNavigateBack = { currentRoute = "profile" }
                    )
                }
            }

            // Bottom Navigation (hide when in profile or settings)
            if (currentRoute != "profile" && currentRoute != "settings") {
                NavigationPanel(
                    currentRoute = currentRoute,
                    onNavigate = { currentRoute = it }
                )
            }
        }
    }
}