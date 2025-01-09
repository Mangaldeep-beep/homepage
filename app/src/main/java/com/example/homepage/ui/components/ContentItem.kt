package com.example.homepage.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

data class ContentItem(
    val title: String,
    val subtitle: String,
    val imageUrl: String,
    val progress: Float = 0f,
    val isDownloaded: Boolean = false,
    val trending: Boolean = false,
    val downloads: String? = null
)

@Composable
fun ContentItem(
    item: ContentItem,
    onClick: () -> Unit = {},
    onDownloadClick: () -> Unit = {}
) {
    val squareSize = 160.dp
    
    Column(
        modifier = Modifier
            .width(squareSize)
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .size(squareSize)  // Make it square
                .clip(RoundedCornerShape(12.dp))
                .background(generateRandomGradient())
        ) {
            // Trending badge
            if (item.trending) {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.8f))
                        .padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.TrendingUp,
                        contentDescription = "Trending",
                        modifier = Modifier.size(16.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "Trending",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            // Download count
            item.downloads?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.8f))
                        .padding(4.dp),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            // Progress indicator
            if (item.progress > 0) {
                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .align(Alignment.BottomCenter)
                ) {
                    val width = size.width
                    val height = size.height
                    
                    // Background line
                    drawLine(
                        color = Color.White.copy(alpha = 0.3f),
                        start = Offset(0f, height / 2),
                        end = Offset(width, height / 2),
                        strokeWidth = height,
                        cap = StrokeCap.Round
                    )
                    
                    // Progress line
                    drawLine(
                        color = Color.White,
                        start = Offset(0f, height / 2),
                        end = Offset(width * item.progress, height / 2),
                        strokeWidth = height,
                        cap = StrokeCap.Round
                    )
                }
            }

            // Download button
            if (!item.isDownloaded) {
                IconButton(
                    onClick = onDownloadClick,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Download,
                        contentDescription = "Download",
                        tint = Color.White
                    )
                }
            }
        }

        // Title and subtitle below the square image
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = item.title,
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Bold
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = item.subtitle,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

private fun generateRandomGradient(): Color {
    return Color(
        red = Random.nextFloat(),
        green = Random.nextFloat(),
        blue = Random.nextFloat(),
        alpha = 0.2f
    )
}