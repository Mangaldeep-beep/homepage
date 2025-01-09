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
    var selectedTab by remember { mutableStateOf("Home") }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        FeaturedPresentation(contentType = selectedTab.ifEmpty { "Home" })
        
        // Tab Section
        TabSection(
            selectedTab = selectedTab,
            onTabSelected = { selectedTab = it }
        )

        when (selectedTab) {
            "" -> {  
                ContentSection(
                    title = "Trending Now",
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
            "Home" -> {
                ContentSection(
                    title = "Trending Now",
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
            "Audiobook" -> {
                ContentSection(
                    title = "Popular Audiobooks",
                    items = listOf(
                        ContentItem(
                            title = "The Power of Now",
                            subtitle = "Eckhart Tolle",
                            imageUrl = "power_now"
                        ),
                        ContentItem(
                            title = "Atomic Habits",
                            subtitle = "James Clear",
                            imageUrl = "atomic_habits"
                        )
                    )
                )
            }
            "E-Book" -> {
                ContentSection(
                    title = "Featured E-Books",
                    items = listOf(
                        ContentItem(
                            title = "The Silent Patient",
                            subtitle = "Alex Michaelides",
                            imageUrl = "silent_patient"
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
                ContentSection(
                    title = "Top Podcasts",
                    items = listOf(
                        ContentItem(
                            title = "The Joe Rogan Experience",
                            subtitle = "Joe Rogan",
                            imageUrl = "jre_podcast"
                        ),
                        ContentItem(
                            title = "Crime Junkie",
                            subtitle = "Ashley Flowers",
                            imageUrl = "crime_junkie"
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