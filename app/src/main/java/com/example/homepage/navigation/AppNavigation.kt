package com.example.homepage.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.homepage.screens.*

@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Audiobook.route,
        modifier = modifier
    ) {
        composable(Screen.Audiobook.route) { AudiobookScreen() }
        composable(Screen.EBook.route) { EBookScreen() }
        composable(Screen.Podcast.route) { PodcastScreen() }
        composable(Screen.Library.route) { LibraryScreen() }
    }
} 