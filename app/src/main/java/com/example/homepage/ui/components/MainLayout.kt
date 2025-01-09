package com.example.homepage.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainLayout(
    currentRoute: String,
    onNavigate: (String) -> Unit,
    content: @Composable () -> Unit
) {
    var isDrawerOpen by remember { mutableStateOf(false) }
    
    ModalNavigationDrawer(
        drawerContent = {
            MenuDrawer(
                isOpen = isDrawerOpen,
                onClose = { isDrawerOpen = false },
                onNavigate = onNavigate
            )
        },
        drawerState = rememberDrawerState(
            initialValue = if (isDrawerOpen) DrawerValue.Open else DrawerValue.Closed
        )
    ) {
        Scaffold(
            bottomBar = {
                NavigationPanel(
                    currentRoute = currentRoute,
                    onNavigate = onNavigate
                )
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier.padding(paddingValues)
            ) {
                content()
            }
        }
    }
}
