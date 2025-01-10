package com.example.homepage.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AnimatedHeader(
    isMenuOpen: Boolean,
    modifier: Modifier = Modifier
) {
    val yellowColor = Color(0xFFFFEB3B)
    
    val scale by animateFloatAsState(
        targetValue = if (isMenuOpen) 1.1f else 1f,
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
        label = "scale"
    )
    
    val rotation by animateFloatAsState(
        targetValue = if (isMenuOpen) 720f else 0f,
        animationSpec = tween(
            durationMillis = 800,
            easing = EaseInOutCirc
        ),
        label = "rotation"
    )

    val translateY by animateFloatAsState(
        targetValue = if (isMenuOpen) 30f else 0f,
        animationSpec = tween(
            durationMillis = 500,
            easing = EaseOutBack
        ),
        label = "translateY"
    )

    val alphaValue by animateFloatAsState(
        targetValue = if (isMenuOpen) 1f else 0f,
        animationSpec = tween(durationMillis = 500),
        label = "alpha"
    )

    Box(
        modifier = modifier
            .wrapContentSize()
            .padding(vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        // Main Title (TREE/MENU)
        Box(
            modifier = Modifier
                .graphicsLayer { 
                    rotationZ = rotation
                    translationY = translateY
                }
        ) {
            AnimatedVisibility(
                visible = !isMenuOpen,
                enter = fadeIn() + scaleIn(),
                exit = fadeOut() + scaleOut(),
                label = "tree text"
            ) {
                Text(
                    text = "TREE",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = yellowColor,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.scale(scale)
                )
            }

            AnimatedVisibility(
                visible = isMenuOpen,
                enter = fadeIn() + scaleIn(),
                exit = fadeOut() + scaleOut(),
                label = "menu text"
            ) {
                Text(
                    text = "MENU",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = yellowColor,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.scale(scale)
                )
            }
        }
    }
}
