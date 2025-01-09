package com.example.homepage.ui.components

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BottomBar(currentRoute: String, onNavigate: (String) -> Unit, modifier: Modifier = Modifier) {
    BottomAppBar(modifier = modifier) {
        Text(text = "Current Route: $currentRoute")
        // Add navigation items as needed
    }
}
