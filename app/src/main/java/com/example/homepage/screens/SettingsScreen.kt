package com.example.homepage.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SettingsScreen(
    onNavigateBack: () -> Unit = {}
) {
    var isDarkTheme by remember { mutableStateOf(true) }
    val yellowColor = Color(0xFFFFEB3B)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // Settings Header with Back Button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onNavigateBack,
                    colors = IconButtonDefaults.iconButtonColors(contentColor = yellowColor)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = yellowColor
                    )
                }
                Text(
                    text = "Settings",
                    color = yellowColor,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        // Category Tabs
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text("GENERAL", color = yellowColor, fontWeight = FontWeight.Bold)
            Text("SUPPORT", color = yellowColor.copy(alpha = 0.6f))
            Text("POLICIES", color = yellowColor.copy(alpha = 0.6f))
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Theme Toggle
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("THEME", color = yellowColor, fontWeight = FontWeight.Medium)
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { isDarkTheme = false },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (!isDarkTheme) yellowColor else Color.DarkGray
                    ),
                    modifier = Modifier.width(100.dp)
                ) {
                    Text("Light", color = if (!isDarkTheme) Color.Black else Color.White)
                }
                Button(
                    onClick = { isDarkTheme = true },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isDarkTheme) yellowColor else Color.DarkGray
                    ),
                    modifier = Modifier.width(100.dp)
                ) {
                    Text("Dark", color = if (isDarkTheme) Color.Black else Color.White)
                }
            }
        }

        // Settings Items
        SettingItem("Notification", yellowColor)
        SettingItem("Privacy", yellowColor)
        SettingItem("Security", yellowColor)
        SettingItem("Help", yellowColor)
        SettingItem("Password", yellowColor)
        SettingItem("Version", yellowColor)
    }
}

@Composable
private fun SettingItem(
    text: String,
    textColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = "Navigate",
            tint = textColor
        )
    }
}
