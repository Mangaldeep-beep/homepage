package com.example.homepage.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.homepage.ui.components.AnimatedBackground
import com.example.homepage.ui.components.BookCard

@Composable
fun LibraryScreen(
    modifier: Modifier = Modifier
) {
    var showAllDownloads by remember { mutableStateOf(false) }
    
    val downloadedBooks = listOf(
        Triple("The Midnight Library", "Matt Haig", 0.8f),
        Triple("Project Hail Mary", "Andy Weir", 0.6f),
        Triple("Dune", "Frank Herbert", 1.0f),
        Triple("The Psychology of Money", "Morgan Housel", 0.4f),
        Triple("The Silent Patient", "Alex Michaelides", 0.9f),
        Triple("The Thursday Murder Club", "Richard Osman", 0.7f)
    )

    Box(modifier = modifier.fillMaxSize()) {
        AnimatedBackground()
        
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item {
                Text(
                    text = "Downloads",
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
                        text = "Downloaded Books",
                        style = MaterialTheme.typography.titleLarge
                    )
                    TextButton(onClick = { showAllDownloads = !showAllDownloads }) {
                        Text(text = if (showAllDownloads) "Show Less ▼" else "See All ▶")
                    }
                }
            }

            items(if (showAllDownloads) downloadedBooks else downloadedBooks.take(3)) { (title, author, progress) ->
                DownloadedBookItem(
                    title = title,
                    author = author,
                    progress = progress,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
            }
        }
    }
}

@Composable
private fun DownloadedBookItem(
    title: String,
    author: String,
    progress: Float,
    modifier: Modifier = Modifier
) {
    var isHovered by remember { mutableStateOf(false) }
    
    val scale by animateFloatAsState(
        targetValue = if (isHovered) 1.02f else 1f,
        label = "scale"
    )

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.1f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = author,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
                Icon(
                    imageVector = Icons.Default.Download,
                    contentDescription = "Downloaded",
                    tint = if (progress >= 1f) 
                        MaterialTheme.colorScheme.primary 
                    else 
                        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            LinearProgressIndicator(
                progress = progress,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
            )
        }
    }
} 