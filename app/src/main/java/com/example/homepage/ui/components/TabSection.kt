package com.example.homepage.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun TabSection(
    selectedTab: String,
    onTabSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val tabs = listOf("Audiobooks", "Ebooks", "Podcast")
    
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(24.dp),
        color = Color(0xFF1F1F1F)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            tabs.forEach { tab ->
                val selected = tab == selectedTab
                
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(
                            if (selected) MaterialTheme.colorScheme.primary
                            else Color.Transparent
                        )
                        .clickable { onTabSelected(tab) }
                        .animateContentSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = tab,
                        modifier = Modifier
                            .padding(vertical = 8.dp),
                        color = if (selected) Color.White 
                               else Color.White.copy(alpha = 0.6f),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = if (selected) FontWeight.Bold 
                                       else FontWeight.Normal
                        )
                    )
                }
            }
        }
    }
}