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
fun TrendingScreen(
    onNavigate: (String) -> Unit = {}
) {
    val scrollState = rememberScrollState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            TopBar(
                onSettingsClick = { /* Handle settings click */ }
            )

            // Trending Books
            SectionHeader(
                title = "Trending Books",
                onSeeAllClick = { /* Handle see all click */ }
            )
            ContentSection(
                items = listOf(
                    ContentItem(
                        title = "Fourth Wing",
                        subtitle = "Rebecca Yarros",
                        imageUrl = "fourth_wing",
                        trending = true
                    ),
                    ContentItem(
                        title = "Iron Flame",
                        subtitle = "Rebecca Yarros",
                        imageUrl = "iron_flame",
                        trending = true
                    )
                )
            )

            // Most Downloaded
            SectionHeader(
                title = "Most Downloaded",
                onSeeAllClick = { /* Handle see all click */ }
            )
            ContentSection(
                items = listOf(
                    ContentItem(
                        title = "Lessons in Chemistry",
                        subtitle = "Bonnie Garmus",
                        imageUrl = "lessons_chemistry",
                        downloads = "50K+"
                    ),
                    ContentItem(
                        title = "The Seven Husbands",
                        subtitle = "Taylor Jenkins Reid",
                        imageUrl = "seven_husbands",
                        downloads = "45K+"
                    )
                )
            )

            Spacer(modifier = Modifier.height(80.dp))
        }
    }
} 