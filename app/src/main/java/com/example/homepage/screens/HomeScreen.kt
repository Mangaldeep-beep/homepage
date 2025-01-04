package com.example.homepage.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.homepage.ui.components.*

@Composable
fun HomeScreen(
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
            TopBar(
                onSettingsClick = { /* Handle settings click */ }
            )

            FeaturedPresentation()
            
            // Tab Section
            TabSection(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )

            when (selectedTab) {
                "Audiobook" -> {
                    // Continue Reading Section
                    ContentSection(
                        title = "Continue Reading",
                        items = listOf(
                            ContentItem(
                                title = "The Lord of the Rings",
                                subtitle = "Chapter 5 - The Bridge of Khazad-dûm",
                                imageUrl = "lotr_cover",
                                progress = 0.45f
                            ),
                            ContentItem(
                                title = "Harry Potter",
                                subtitle = "Chapter 3 - The Letters from No One",
                                imageUrl = "hp_cover",
                                progress = 0.25f
                            )
                        )
                    )
                }
                "E-Book" -> {
                    // Popular E-Books Section
                    ContentSection(
                        title = "Popular E-Books",
                        items = listOf(
                            ContentItem(
                                title = "The Midnight Library",
                                subtitle = "Matt Haig",
                                imageUrl = "midnight_library"
                            ),
                            ContentItem(
                                title = "Project Hail Mary",
                                subtitle = "Andy Weir",
                                imageUrl = "hail_mary"
                            )
                        )
                    )
                }
                "Podcast" -> {
                    // Trending Podcasts Section
                    ContentSection(
                        title = "Trending Podcasts",
                        items = listOf(
                            ContentItem(
                                title = "Serial",
                                subtitle = "True Crime",
                                imageUrl = "serial_podcast"
                            ),
                            ContentItem(
                                title = "This American Life",
                                subtitle = "Documentary",
                                imageUrl = "tal_podcast"
                            )
                        )
                    )
                }
            }

            // Popular Now Section (Common for all tabs)
            ContentSection(
                title = "Popular Now",
                items = listOf(
                    ContentItem(
                        title = "The Midnight Library",
                        subtitle = "Matt Haig",
                        imageUrl = "midnight_library"
                    ),
                    ContentItem(
                        title = "Project Hail Mary",
                        subtitle = "Andy Weir",
                        imageUrl = "hail_mary"
                    )
                )
            )

            // New Releases Section (Common for all tabs)
            ContentSection(
                title = "New Releases",
                items = listOf(
                    ContentItem(
                        title = "Tomorrow Will Be Different",
                        subtitle = "Sarah McBride",
                        imageUrl = "tomorrow_different"
                    ),
                    ContentItem(
                        title = "The Paris Apartment",
                        subtitle = "Lucy Foley",
                        imageUrl = "paris_apartment"
                    )
                )
            )
            
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
} 