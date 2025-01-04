package com.example.homepage.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.Alignment
import androidx.compose.ui.zIndex
import com.example.homepage.ui.components.AnimatedBackground
import com.example.homepage.ui.components.NavigationPanel
import com.example.homepage.screens.*

@Composable
fun MainScreen() {
    var currentRoute by remember { mutableStateOf("home") }

    Box(modifier = Modifier.fillMaxSize()) {
        // Animated Background
        AnimatedBackground()

        // Main layout with content and bottom navigation
        Column(modifier = Modifier.fillMaxSize()) {
            // Fixed top text
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .zIndex(1f)
                    .padding(start = 16.dp, top = 16.dp)
            ) {
                Text(
                    text = "tree pull",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
            }

            // Content Area
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                when (currentRoute) {
                    "home" -> HomeScreen()
                    "trending" -> TrendingScreen()
                    "profile" -> ProfileScreen()
                    "search" -> SearchScreen()
                    "library" -> LibraryScreen()
                }
            }

            // Bottom Navigation Panel
            NavigationPanel(
                currentRoute = currentRoute,
                onNavigate = { route -> currentRoute = route },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            )
        }
    }
}

@Composable
private fun ProfileScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Profile Screen",
            style = MaterialTheme.typography.headlineMedium
        )
    }
} 