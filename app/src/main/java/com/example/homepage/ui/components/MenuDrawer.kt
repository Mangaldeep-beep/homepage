package com.example.homepage.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable

@Composable
fun MenuDrawer(
    isOpen: Boolean,
    onClose: () -> Unit,
    onNavigate: (String) -> Unit
) {
    ModalDrawerSheet {
        Spacer(modifier = Modifier.height(16.dp))
        
        // Profile Section
        ListItem(
            headlineContent = { Text("Profile") },
            leadingContent = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            modifier = Modifier
                .clickable(
                    onClick = {
                        onNavigate("profile")
                        onClose()
                    }
                )
        )
        
        // Library Section
        ListItem(
            headlineContent = { Text("My Library") },
            leadingContent = { Icon(Icons.Default.LibraryBooks, contentDescription = "Library") },
            modifier = Modifier
                .clickable(
                    onClick = {
                        onNavigate("library")
                        onClose()
                    }
                )
        )
        
        // Downloads Section
        ListItem(
            headlineContent = { Text("Downloads") },
            leadingContent = { Icon(Icons.Default.Download, contentDescription = "Downloads") },
            modifier = Modifier
                .clickable(
                    onClick = {
                        onNavigate("downloads")
                        onClose()
                    }
                )
        )
        
        // Settings Section
        ListItem(
            headlineContent = { Text("Settings") },
            leadingContent = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
            modifier = Modifier
                .clickable(
                    onClick = {
                        onNavigate("settings")
                        onClose()
                    }
                )
        )
        
        Divider(modifier = Modifier.padding(vertical = 8.dp))
        
        // Help & Support
        ListItem(
            headlineContent = { Text("Help & Support") },
            leadingContent = { Icon(Icons.Default.Help, contentDescription = "Help") },
            modifier = Modifier
                .clickable(
                    onClick = {
                        onNavigate("help")
                        onClose()
                    }
                )
        )
        
        // About
        ListItem(
            headlineContent = { Text("About") },
            leadingContent = { Icon(Icons.Default.Info, contentDescription = "About") },
            modifier = Modifier
                .clickable(
                    onClick = {
                        onNavigate("about")
                        onClose()
                    }
                )
        )
    }
}
