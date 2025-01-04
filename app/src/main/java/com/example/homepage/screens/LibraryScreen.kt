package com.example.homepage.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.homepage.ui.components.*

@Composable
fun LibraryScreen(
    onNavigate: (String) -> Unit = {}
) {
    var currentRoute by remember { mutableStateOf("library") }
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

            // Downloaded Books
            SectionHeader(
                title = "Downloaded Books",
                onSeeAllClick = { /* Handle see all click */ }
            )
            ContentSection(
                items = listOf(
                    ContentItem(
                        title = "The Silent Patient",
                        subtitle = "Alex Michaelides",
                        imageUrl = "silent_patient",
                        isDownloaded = true
                    ),
                    ContentItem(
                        title = "Verity",
                        subtitle = "Colleen Hoover",
                        imageUrl = "verity",
                        isDownloaded = true
                    )
                )
            )

            // Reading Lists
            SectionHeader(
                title = "My Reading Lists",
                onSeeAllClick = { /* Handle see all click */ }
            )
            ContentSection(
                items = listOf(
                    ContentItem(
                        title = "Summer Reading",
                        subtitle = "12 Books",
                        imageUrl = "summer_list"
                    ),
                    ContentItem(
                        title = "Must Read",
                        subtitle = "8 Books",
                        imageUrl = "must_read"
                    )
                )
            )

            // Currently Reading
            SectionHeader(
                title = "Currently Reading",
                onSeeAllClick = { /* Handle see all click */ }
            )
            ContentSection(
                items = listOf(
                    ContentItem(
                        title = "The Alchemist",
                        subtitle = "Paulo Coelho",
                        imageUrl = "alchemist",
                        progress = 0.75f
                    ),
                    ContentItem(
                        title = "1984",
                        subtitle = "George Orwell",
                        imageUrl = "1984",
                        progress = 0.30f
                    )
                )
            )

            Spacer(modifier = Modifier.height(80.dp))
        }

        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            BottomBar(
                currentRoute = currentRoute,
                onNavigate = { route ->
                    currentRoute = route
                    onNavigate(route)
                }
            )
        }
    }
} 