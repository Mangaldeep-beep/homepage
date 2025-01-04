package com.example.homepage.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.homepage.ui.components.*

@Composable
fun SearchScreen(
    onNavigate: (String) -> Unit = {}
) {
    var currentRoute by remember { mutableStateOf("search") }
    val scrollState = rememberScrollState()
    var searchQuery by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            TopBar(
                onSettingsClick = { /* Handle settings click */ }
            )

            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = { Text("Search books, authors...") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                },
                singleLine = true
            )

            // Popular Categories
            SectionHeader(
                title = "Popular Categories",
                onSeeAllClick = { /* Handle see all click */ }
            )
            ContentSection(
                items = listOf(
                    ContentItem(
                        title = "Fiction",
                        subtitle = "1.2K+ Books",
                        imageUrl = "fiction_category"
                    ),
                    ContentItem(
                        title = "Non-Fiction",
                        subtitle = "850+ Books",
                        imageUrl = "nonfiction_category"
                    )
                )
            )

            // Recent Searches
            SectionHeader(
                title = "Recent Searches",
                onSeeAllClick = { /* Handle see all click */ }
            )
            ContentSection(
                items = listOf(
                    ContentItem(
                        title = "Stephen King",
                        subtitle = "Author",
                        imageUrl = "author_king"
                    ),
                    ContentItem(
                        title = "Fantasy Books",
                        subtitle = "Category",
                        imageUrl = "fantasy_category"
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