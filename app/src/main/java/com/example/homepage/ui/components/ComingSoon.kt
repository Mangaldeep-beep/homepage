package com.example.homepage.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kotlinx.coroutines.delay

@Composable
fun ComingSoonDialog(
    onDismiss: () -> Unit
) {
    var dots by remember { mutableStateOf("") }

    LaunchedEffect(key1 = true) {
        while (true) {
            dots = when (dots) {
                "" -> "."
                "." -> ".."
                ".." -> "..."
                else -> ""
            }
            delay(500) // Change dots every 500ms
        }
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.7f),
            shape = RoundedCornerShape(16.dp),
            color = Color.Black
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Loading Animation Boxes
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    repeat(3) {
                        LoadingBox()
                    }
                }

                // Loading Text
                Text(
                    text = "Loading$dots",
                    color = Color.Yellow,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Coming Soon Text
                Text(
                    text = "Coming Soon",
                    color = Color.Yellow,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )

                // Close Button
                TextButton(
                    onClick = onDismiss,
                    modifier = Modifier.padding(top = 32.dp),
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = Color.Yellow
                    )
                ) {
                    Text(
                        text = "Close",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
private fun LoadingBox() {
    var isAnimating by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        while (true) {
            isAnimating = !isAnimating
            delay(1000) // Toggle every second
        }
    }

    Box(
        modifier = Modifier
            .size(80.dp)
            .background(
                color = if (isAnimating) Color.Yellow else Color.Yellow.copy(alpha = 0.3f),
                shape = RoundedCornerShape(8.dp)
            )
    )
}
