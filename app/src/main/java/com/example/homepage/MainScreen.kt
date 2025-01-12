package com.example.homepage

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.homepage.navigation.AppNavigation
import com.example.homepage.navigation.Screen
import com.example.homepage.ui.components.AnimatedBackground
import com.example.homepage.ui.components.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var showMenu by remember { mutableStateOf(false) }
    val navController = rememberNavController()

    Box(modifier = Modifier.fillMaxSize()) {
        // Animated Background
        AnimatedBackground()

        // Main Content
        Column(modifier = Modifier.fillMaxSize()) {
            TopBar(
                onMenuClick = { showMenu = !showMenu },
                onProfileClick = { /* Handle profile click */ },
                showMenu = showMenu
            )

            // Content
            Box(modifier = Modifier.weight(1f)) {
                AppNavigation(
                    navController = navController,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}