package com.example.homepage.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.homepage.ui.components.*

@Composable
@Preview
fun DownloadsScreen(
    onNavigate: (String) -> Unit = {}
) {
    var selectedTab by remember { mutableStateOf("Audiobook") }
    val scrollState = rememberScrollState()

    Box(modifier = Modifier.fillMaxSize()) {
        // Animated Background with lower z-index
        Box(modifier = Modifier.fillMaxSize()) {
            AnimatedBackground()
        }
        
        // Content with higher z-index
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            // Tab Section
            TabSection(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )

            when (selectedTab) {
                "Audiobook" -> {
                    ContentSection(
                        title = "Downloaded Audiobooks",
                        items = listOf(
                            ContentItem(
                                title = "The Sandman",
                                subtitle = "Neil Gaiman",
                                imageUrl = "sandman",
                                isDownloaded = true
                            ),
                            ContentItem(
                                title = "Atomic Habits",
                                subtitle = "James Clear",
                                imageUrl = "atomic_habits",
                                isDownloaded = true
                            )
                        )
                    )
                }
                "E-Book" -> {
                    ContentSection(
                        title = "Downloaded E-Books",
                        items = listOf(
                            ContentItem(
                                title = "Dune",
                                subtitle = "Frank Herbert",
                                imageUrl = "dune",
                                isDownloaded = true
                            ),
                            ContentItem(
                                title = "The Hobbit",
                                subtitle = "J.R.R. Tolkien",
                                imageUrl = "hobbit",
                                isDownloaded = true
                            )
                        )
                    )
                }
                "Podcast" -> {
                    ContentSection(
                        title = "Downloaded Podcasts",
                        items = listOf(
                            ContentItem(
                                title = "The Daily",
                                subtitle = "News",
                                imageUrl = "daily_podcast",
                                isDownloaded = true
                            ),
                            ContentItem(
                                title = "Hardcore History",
                                subtitle = "Dan Carlin",
                                imageUrl = "history_podcast",
                                isDownloaded = true
                            )
                        )
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(80.dp))
        }

        // Bottom navigation
        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            BottomBar(
                currentRoute = "downloads",
                onNavigate = onNavigate
            )
        }
    }
}