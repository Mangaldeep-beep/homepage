package com.example.homepage.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun TabSection(
    selectedTab: String,
    onTabSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val tabs = listOf("Audiobook", "E-Book", "Podcast")
    
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.1f)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            tabs.forEach { tab ->
                val selected = tab == selectedTab
                val animatedScale by animateFloatAsState(
                    targetValue = if (selected) 1.2f else 1f,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    ),
                    label = "scale"
                )
                
                val color by animateColorAsState(
                    targetValue = if (selected) 
                        MaterialTheme.colorScheme.primary 
                    else 
                        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    ),
                    label = "color"
                )

                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.scale(animatedScale)
                    ) {
                        TextButton(
                            onClick = { 
                                if (selected) {
                                    onTabSelected("")  // Deselect if already selected
                                } else {
                                    onTabSelected(tab)
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            colors = ButtonDefaults.textButtonColors(
                                contentColor = color
                            )
                        ) {
                            Text(
                                text = tab,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
                                ),
                                color = color
                            )
                        }

                        if (selected) {
                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 24.dp)
                                    .height(2.dp)
                                    .width(32.dp)
                                    .background(
                                        color = MaterialTheme.colorScheme.primary,
                                        shape = RoundedCornerShape(1.dp)
                                    )
                            )
                        }
                    }
                }
            }
        }
    }
}