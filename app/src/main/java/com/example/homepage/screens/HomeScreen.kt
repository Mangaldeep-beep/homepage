package com.example.homepage.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.homepage.ui.components.*

@Composable
fun HomeScreen(
    onNavigate: (String) -> Unit = {}
) {
    var selectedTab by remember { mutableStateOf("Audiobooks") }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        FeaturedPresentation(contentType = selectedTab)
        
        // Tab Section
        TabSection(
            selectedTab = selectedTab,
            onTabSelected = { selectedTab = it }
        )

        when (selectedTab) {
            "Audiobooks" -> {
                ContentSection(
                    title = "Popular Audiobooks",
                    items = listOf(
                        ContentItem(
                            title = "The Lord of the Rings",
                            subtitle = "J.R.R. Tolkien",
                            imageUrl = "lotr_cover"
                        ),
                        ContentItem(
                            title = "Harry Potter",
                            subtitle = "J.K. Rowling",
                            imageUrl = "hp_cover"
                        )
                    )
                )
            }
            "Ebooks" -> {
                ContentSection(
                    title = "Featured Ebooks",
                    items = listOf(
                        ContentItem(
                            title = "Dune",
                            subtitle = "Frank Herbert",
                            imageUrl = "dune_cover"
                        ),
                        ContentItem(
                            title = "1984",
                            subtitle = "George Orwell",
                            imageUrl = "1984_cover"
                        )
                    )
                )
            }
            "Podcast" -> {
                ContentSection(
                    title = "Top Podcasts",
                    items = listOf(
                        ContentItem(
                            title = "Tech Talk Daily",
                            subtitle = "Daily Tech News",
                            imageUrl = "tech_talk_cover"
                        ),
                        ContentItem(
                            title = "Science Hour",
                            subtitle = "Latest in Science",
                            imageUrl = "science_hour_cover"
                        )
                    )
                )
            }
        }

        // Popular Now Section
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

        // New Releases Section
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