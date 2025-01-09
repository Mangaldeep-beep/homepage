package com.example.homepage.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.NavigateBefore
import androidx.compose.material.icons.automirrored.filled.NavigateNext
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import com.example.homepage.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.ui.graphics.graphicsLayer
import kotlin.math.absoluteValue

data class FeaturedItem(
    val title: String,
    val subtitle: String,
    val rating: String,
    val year: String,
    val genre: String,
    val imageRes: Int
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FeaturedPresentation(
    modifier: Modifier = Modifier,
    contentType: String = "Audiobooks"
) {
    val items = remember(contentType) {
        when (contentType) {
            "Audiobooks" -> listOf(
                FeaturedItem(
                    "The Power of Now",
                    "Eckhart Tolle",
                    "4.8 ★",
                    "2023",
                    "Self Help",
                    R.drawable.book_placeholder
                ),
                FeaturedItem(
                    "Atomic Habits",
                    "James Clear",
                    "4.9 ★",
                    "2023",
                    "Personal Development",
                    R.drawable.book_placeholder
                ),
                FeaturedItem(
                    "Think Like a Monk",
                    "Jay Shetty",
                    "4.6 ★",
                    "2023",
                    "Spirituality",
                    R.drawable.book_placeholder
                )
            )
            "Ebooks" -> listOf(
                FeaturedItem(
                    "The Silent Patient",
                    "Alex Michaelides",
                    "4.7 ★",
                    "2023",
                    "Thriller",
                    R.drawable.book_placeholder
                ),
                FeaturedItem(
                    "The Psychology of Money",
                    "Morgan Housel",
                    "4.8 ★",
                    "2023",
                    "Finance",
                    R.drawable.book_placeholder
                ),
                FeaturedItem(
                    "Project Hail Mary",
                    "Andy Weir",
                    "4.9 ★",
                    "2023",
                    "Sci-Fi",
                    R.drawable.book_placeholder
                )
            )
            "Podcast" -> listOf(
                FeaturedItem(
                    "The Joe Rogan",
                    "Experience",
                    "4.8 ★",
                    "2023",
                    "Talk Show",
                    R.drawable.book_placeholder
                ),
                FeaturedItem(
                    "Crime Junkie",
                    "True Crime Stories",
                    "4.7 ★",
                    "2023",
                    "True Crime",
                    R.drawable.book_placeholder
                ),
                FeaturedItem(
                    "TED Radio Hour",
                    "NPR",
                    "4.9 ★",
                    "2023",
                    "Educational",
                    R.drawable.book_placeholder
                )
            )
            else -> listOf(
                FeaturedItem(
                    "Featured Content",
                    "Explore More",
                    "4.5 ★",
                    "2023",
                    "Various",
                    R.drawable.book_placeholder
                )
            )
        }
    }

    var isAutoScrollEnabled by remember { mutableStateOf(true) }
    val pagerState = rememberPagerState(pageCount = { items.size })
    val scope = rememberCoroutineScope()

    // Auto-scroll with optimized delay
    LaunchedEffect(isAutoScrollEnabled) {
        if (isAutoScrollEnabled) {
            while (true) {
                delay(10000)
                if (!isAutoScrollEnabled) break
                try {
                    val nextPage = (pagerState.currentPage + 1) % items.size
                    pagerState.animateScrollToPage(nextPage)
                } catch (e: Exception) {
                    break
                }
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            pageSpacing = (-25).dp,
            contentPadding = PaddingValues(horizontal = 32.dp)
        ) { page ->
            val currentItem = items[page]
            val pageOffset = (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
            val scaleFactor = 0.85f + (1f - 0.85f) * (1f - pageOffset.absoluteValue.coerceIn(0f, 1f))
            
            FeaturedCard(
                item = currentItem,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .graphicsLayer {
                        scaleX = scaleFactor
                        scaleY = scaleFactor
                        alpha = scaleFactor
                    }
            ) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    // Bottom Controls
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(bottom = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Add to Playlist Button
                            IconButton(
                                onClick = { /* Handle add to playlist */ },
                                modifier = Modifier.size(40.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_add_to_playlist),
                                    contentDescription = "Add to Playlist",
                                    modifier = Modifier.size(40.dp)
                                )
                            }

                            // Play/Pause Button
                            IconButton(
                                onClick = { isAutoScrollEnabled = !isAutoScrollEnabled },
                                modifier = Modifier
                                    .size(56.dp)
                                    .background(
                                        color = if (isAutoScrollEnabled) Color.Red else Color.Green,
                                        shape = CircleShape
                                    )
                            ) {
                                Icon(
                                    imageVector = if (isAutoScrollEnabled) Icons.Default.Pause else Icons.Default.PlayArrow,
                                    contentDescription = if (isAutoScrollEnabled) "Pause" else "Play",
                                    tint = Color.White,
                                    modifier = Modifier.size(32.dp)
                                )
                            }
                        }

                        // Content and Rating Pills
                        Column(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(start = 12.dp, bottom = 24.dp)
                        ) {
                            // Rating Pills
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                RatingPill(text = currentItem.rating)
                                RatingPill(text = currentItem.year)
                                RatingPill(text = currentItem.genre)
                            }
                        }

                        // Music Visualizer Effect
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(20.dp)
                                .padding(horizontal = 16.dp)
                                .align(Alignment.BottomCenter),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            repeat(30) { index ->
                                val infiniteTransition = rememberInfiniteTransition(label = "bar$index")
                                val height by infiniteTransition.animateFloat(
                                    initialValue = 2f,
                                    targetValue = 16f,
                                    animationSpec = infiniteRepeatable(
                                        animation = tween(
                                            durationMillis = 500 + (index * 50),
                                            easing = FastOutSlowInEasing
                                        ),
                                        repeatMode = RepeatMode.Reverse
                                    ),
                                    label = "height$index"
                                )

                                val color = when (index) {
                                    in 0..9 -> Color(0xFFFF1744)    // Red
                                    in 10..19 -> Color(0xFF2979FF)  // Blue
                                    else -> Color(0xFFAA00FF)       // Purple
                                }.copy(alpha = 0.8f)

                                Box(
                                    modifier = Modifier
                                        .width(3.dp)
                                        .height(height.dp)
                                        .background(
                                            color = color,
                                            shape = RoundedCornerShape(8.dp)
                                        )
                                )
                            }
                        }
                    }
                }
            }
        }

        // Navigation Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Previous Button
            IconButton(
                onClick = {
                    scope.launch {
                        val targetPage = (pagerState.currentPage - 1).coerceAtLeast(0)
                        pagerState.animateScrollToPage(targetPage)
                    }
                },
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.Black.copy(alpha = 0.5f))
                    .size(32.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.NavigateBefore,
                    contentDescription = "Previous",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }

            // Next Button
            IconButton(
                onClick = {
                    scope.launch {
                        val targetPage = (pagerState.currentPage + 1) % items.size
                        pagerState.animateScrollToPage(targetPage)
                    }
                },
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.Black.copy(alpha = 0.5f))
                    .size(32.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.NavigateNext,
                    contentDescription = "Next",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        // Bouncing Down Arrow with Beat Animation
        val arrowTransition = rememberInfiniteTransition(label = "arrow")
        val arrowScale by arrowTransition.animateFloat(
            initialValue = 1f,
            targetValue = 1.2f,
            animationSpec = infiniteRepeatable(
                animation = tween(500, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "arrow_scale"
        )

        Icon(
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = "Scroll Down",
            tint = Color.Red,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = 6.dp)
                .size(24.dp)
                .scale(arrowScale)
        )
    }
}

@Composable
private fun FeaturedCard(
    item: FeaturedItem,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit = {}
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
    ) {
        // Main Image
        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = "Featured Movie",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Gradient Overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.7f)
                        )
                    )
                )
        )

        content()
    }
}

@Composable
private fun PreviewImage(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.book_placeholder),
        contentDescription = "Preview",
        modifier = modifier
                    .width(60.dp)
                    .height(100.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .alpha(0.6f),
                contentScale = ContentScale.Crop
            )
        }

@Composable
private fun RatingPill(
    text: String,
    modifier: Modifier = Modifier
            ) {
                Surface(
                    color = Color.Red.copy(alpha = 0.3f),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
                ) {
                    Text(
            text = text,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        color = Color.White,
                        fontSize = 12.sp
                    )
    }
}