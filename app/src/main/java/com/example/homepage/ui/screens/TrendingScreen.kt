package com.example.homepage.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.homepage.ui.components.AnimatedBackground
import com.example.homepage.ui.components.BookCard
import com.example.homepage.R

@Composable
fun TrendingContent() {
    var showAllContent by remember { mutableStateOf(false) }
    
    val trendingBooks = listOf(
        BookData(
            title = "Fourth Wing",
            author = "Rebecca Yarros",
            imageRes = R.drawable.book_placeholder,
            onClick = {}
        ),
        BookData(
            title = "Iron Flame",
            author = "Rebecca Yarros",
            imageRes = R.drawable.book_placeholder,
            onClick = {}
        ),
        BookData(
            title = "House of Flame and Shadow",
            author = "Sarah J. Maas",
            imageRes = R.drawable.book_placeholder,
            onClick = {}
        ),
        BookData(
            title = "A Court of Thorns and Roses",
            author = "Sarah J. Maas",
            imageRes = R.drawable.book_placeholder,
            onClick = {}
        ),
        BookData(
            title = "The Woman in Me",
            author = "Britney Spears",
            imageRes = R.drawable.book_placeholder,
            onClick = {}
        ),
        BookData(
            title = "Atomic Habits",
            author = "James Clear",
            imageRes = R.drawable.book_placeholder,
            onClick = {}
        ),
        BookData(
            title = "The Creative Act",
            author = "Rick Rubin",
            imageRes = R.drawable.book_placeholder,
            onClick = {}
        ),
        BookData(
            title = "The Boys in the Boat",
            author = "Daniel James Brown",
            imageRes = R.drawable.book_placeholder,
            onClick = {}
        )
    )

    val mostDownloaded = listOf(
        BookData(
            title = "Lessons in Chemistry",
            author = "Bonnie Garmus",
            imageRes = R.drawable.book_placeholder,
            onClick = {}
        ),
        BookData(
            title = "The Seven Husbands",
            author = "Taylor Jenkins Reid",
            imageRes = R.drawable.book_placeholder,
            onClick = {}
        ),
        BookData(
            title = "Tomorrow",
            author = "Gabrielle Zevin",
            imageRes = R.drawable.book_placeholder,
            onClick = {}
        ),
        BookData(
            title = "Happy Place",
            author = "Emily Henry",
            imageRes = R.drawable.book_placeholder,
            onClick = {}
        ),
        BookData(
            title = "Demon Copperhead",
            author = "Barbara Kingsolver",
            imageRes = R.drawable.book_placeholder,
            onClick = {}
        )
    )

    Box(modifier = Modifier.fillMaxSize()) {
        AnimatedBackground()
        
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item {
                Text(
                    text = "Trending Now",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Trending Books",
                        style = MaterialTheme.typography.titleLarge
                    )
                    TextButton(onClick = { showAllContent = !showAllContent }) {
                        Text(text = if (showAllContent) "Show Less ▼" else "See All ▶")
                    }
                }
            }

            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    items(if (showAllContent) trendingBooks else trendingBooks.take(4)) { book ->
                        BookCard(
                            title = book.title,
                            author = book.author,
                            imageRes = book.imageRes,
                            onClick = book.onClick,
                            modifier = Modifier.width(160.dp)
                        )
                    }
                }
            }

            item {
                Text(
                    text = "Most Downloaded",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(top = 24.dp, bottom = 16.dp)
                )
            }

            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    items(mostDownloaded) { book ->
                        BookCard(
                            title = book.title,
                            author = book.author,
                            imageRes = book.imageRes,
                            onClick = book.onClick,
                            modifier = Modifier.width(160.dp)
                        )
                    }
                }
            }
        }
    }
}

private data class BookData(
    val title: String,
    val author: String,
    val imageRes: Int,
    val onClick: () -> Unit
)