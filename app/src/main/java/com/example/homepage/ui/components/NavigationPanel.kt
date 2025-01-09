package com.example.homepage.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NavigationPanel(
    currentRoute: String,
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(horizontal = 24.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavItem(
                icon = Icons.Outlined.Home,
                isSelected = currentRoute == "home",
                onClick = { onNavigate("home") }
            )
            
            NavItem(
                icon = Icons.Outlined.TrendingUp,
                isSelected = currentRoute == "trending",
                onClick = { onNavigate("trending") }
            )
            
            NavItem(
                icon = Icons.Outlined.Search,
                isSelected = currentRoute == "search",
                onClick = { onNavigate("search") }
            )
            
            NavItem(
                icon = Icons.Outlined.PlayCircle,
                isSelected = currentRoute == "videos",
                onClick = { onNavigate("videos") }
            )
            
            NavItem(
                icon = Icons.Outlined.Group,
                isSelected = currentRoute == "community",
                onClick = { onNavigate("community") }
            )
            
            NavItem(
                icon = Icons.Outlined.Notifications,
                isSelected = currentRoute == "notification",
                onClick = { onNavigate("notification") }
            )
        }
    }
}

@Composable
private fun NavItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val color = if (isSelected) 
        Color.White
    else 
        Color.Gray.copy(alpha = 0.6f)

    IconButton(
        onClick = onClick,
        modifier = Modifier.size(40.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(24.dp)
        )
    }
}