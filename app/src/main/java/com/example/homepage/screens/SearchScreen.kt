package com.example.homepage.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homepage.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen() {
    var searchQuery by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            // Search Bar
            item {
                Text(
                    "Suggestions",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            // Categories Grid
            item {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.height(200.dp)
                ) {
                    items(categories) { category ->
                        CategoryCard(
                            title = category.title,
                            backgroundColor = category.color
                        )
                    }
                }
            }

            // Discover Section
            item {
                Text(
                    "Discover something new",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }

            // Browse Row
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.height(150.dp)
                ) {
                    items(browseItems) { item ->
                        BrowseCard(
                            imageUrl = item.imageUrl,
                            tag = item.tag
                        )
                    }
                }
            }

            // Year Sections
            item {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .height(100.dp)
                ) {
                    item { YearCard(title = "2024 in Music", color = Color(0xFF1DB954)) }
                    item { YearCard(title = "2024 in Podcasts", color = Color(0xFFE1306C)) }
                }
            }

            // Features Section
            item {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .height(100.dp)
                ) {
                    item { FeatureCard(title = "Made For You", color = Color(0xFF0A4DA2)) }
                    item { FeatureCard(title = "New Releases", color = Color(0xFF2E8B57)) }
                }
            }

            // Language Section
            item {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.height(200.dp)
                ) {
                    items(languages) { language ->
                        LanguageCard(
                            title = language.name,
                            color = language.color,
                            imageUrl = language.imageUrl
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CategoryCard(
    title: String,
    backgroundColor: Color
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun BrowseCard(
    imageUrl: String,
    tag: String
) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .fillMaxHeight(),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            Image(
                painter = painterResource(id = R.drawable.preview_placeholder),
                contentDescription = tag,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = "#$tag",
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun YearCard(title: String, color: Color) {
    Card(
        modifier = Modifier.fillMaxSize(),
        colors = CardDefaults.cardColors(containerColor = color)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun FeatureCard(title: String, color: Color) {
    Card(
        modifier = Modifier.fillMaxSize(),
        colors = CardDefaults.cardColors(containerColor = color)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun LanguageCard(
    title: String,
    color: Color,
    imageUrl: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        colors = CardDefaults.cardColors(containerColor = color)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color)
        ) {
            Image(
                painter = painterResource(id = R.drawable.featured_placeholder),
                contentDescription = title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = title,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
            )
        }
    }
}

private data class Category(val title: String, val color: Color)
private data class BrowseItem(val imageUrl: String, val tag: String)
private data class Language(val name: String, val color: Color, val imageUrl: String)

private val categories = listOf(
    Category("For You", Color(0xFF1E88E5)),
    Category("Podcasts", Color(0xFFE91E63)),
    Category("Ebooks", Color(0xFFE65100)),
    Category("AudioBooks", Color(0xFFFFB300))
)

private val browseItems = listOf(
    BrowseItem("https://example.com/hindi_hiphop.jpg", "hindi hip hop"),
    BrowseItem("https://example.com/urdu_hiphop.jpg", "urdu hip hop"),
    BrowseItem("https://example.com/phonk.jpg", "phonk")
)

private val languages = listOf(
    Language("Hindi", Color(0xFFE91E63), "https://example.com/hindi.jpg"),
    Language("Punjabi", Color(0xFFE91E63), "https://example.com/punjabi.jpg"),
    Language("Tamil", Color(0xFFE65100), "https://example.com/tamil.jpg"),
    Language("Telugu", Color(0xFFE65100), "https://example.com/telugu.jpg")
)