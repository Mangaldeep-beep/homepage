package com.example.homepage.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homepage.R

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

@Composable
fun FeaturedPresentation(
    modifier: Modifier = Modifier,
    onPlayClick: () -> Unit = {}
) {
    var isHovered by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isHovered) 1.02f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "scale"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
            .clip(RoundedCornerShape(16.dp))
            .scale(scale)
            .clickable { isHovered = !isHovered }
    ) {
        // Main Image
        Image(
            painter = painterResource(id = R.drawable.book_placeholder),
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

        // Side Preview Images
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PreviewImage()
            PreviewImage()
        }

        // Content
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            // Rating Pills
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                RatingPill(text = "4.5 ★")
                RatingPill(text = "2023")
                RatingPill(text = "Horror")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Title
            Text(
                text = "The Exorcism of",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                ),
                color = Color.White
            )
            Text(
                text = "Anneliese Michel",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                ),
                color = Color.White
            )
        }

        // Play Button (Red Circle)
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .size(48.dp)
                .clip(CircleShape)
                .background(Color.Red)
                .clickable(onClick = onPlayClick),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "Play",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}