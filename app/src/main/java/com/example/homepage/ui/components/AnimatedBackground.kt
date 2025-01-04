package com.example.homepage.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.graphicsLayer
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

private const val PI = 3.14159f

@Composable
fun AnimatedBackground(
    modifier: Modifier = Modifier
) {
    val bubbles = remember {
        List(15) {
            Bubble(
                x = Random.nextFloat() * 1000,
                y = Random.nextFloat() * 2000,
                radius = Random.nextFloat() * 40 + 20,
                speed = Random.nextFloat() * 2 + 1,
                color = Color(
                    red = Random.nextFloat(),
                    green = Random.nextFloat(),
                    blue = Random.nextFloat(),
                    alpha = 0.3f
                )
            )
        }
    }

    val infiniteTransition = rememberInfiniteTransition(label = "background")
    val time by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 10000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "time"
    )

    Box(modifier = modifier.fillMaxSize()) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(alpha = 0.99f)
        ) {
            // Draw each bubble
            bubbles.forEach { bubble ->
                val x = bubble.x + cos(time * 2 * PI * bubble.speed).toFloat() * 50
                val y = bubble.y + sin(time * 2 * PI * bubble.speed).toFloat() * 50
                
                drawCircle(
                    color = bubble.color,
                    radius = bubble.radius,
                    center = Offset(x, y)
                )
            }

            // Draw additional floating shapes
            drawFloatingShapes(time)
        }
    }
}

private fun DrawScope.drawFloatingShapes(time: Float) {
    val primaryColor = Color(0xFFFFD700) // Gold color
    val secondaryColor = Color(0xFF4A90E2) // Blue color
    
    // Draw floating triangles
    for (i in 0..5) {
        val x = size.width * (0.2f + 0.6f * cos(time * 2 * PI + i).toFloat())
        val y = size.height * (0.2f + 0.6f * sin(time * 2 * PI + i).toFloat())
        
        drawCircle(
            color = primaryColor.copy(alpha = 0.1f),
            radius = 30f + 20f * sin(time * 2 * PI + i).toFloat(),
            center = Offset(x, y)
        )
    }

    // Draw floating circles
    for (i in 0..8) {
        val x = size.width * (0.3f + 0.4f * sin(time * 2 * PI + i).toFloat())
        val y = size.height * (0.3f + 0.4f * cos(time * 2 * PI + i).toFloat())
        
        drawCircle(
            color = secondaryColor.copy(alpha = 0.1f),
            radius = 40f + 30f * cos(time * 2 * PI + i).toFloat(),
            center = Offset(x, y)
        )
    }
}

private data class Bubble(
    val x: Float,
    val y: Float,
    val radius: Float,
    val speed: Float,
    val color: Color
) 