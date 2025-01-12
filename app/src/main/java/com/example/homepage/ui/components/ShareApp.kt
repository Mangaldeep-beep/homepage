package com.example.homepage.ui.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun ShareAppDialog(
    onDismiss: () -> Unit
) {
    var currentTask by remember { mutableStateOf(0) }
    var task1Completed by remember { mutableStateOf(false) }
    var task2Completed by remember { mutableStateOf(false) }
    var screenshotsUploaded by remember { mutableStateOf(0) }
    var adsWatched by remember { mutableStateOf(0) }
    val context = LocalContext.current

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            screenshotsUploaded++
            if (screenshotsUploaded >= 2) {
                task1Completed = true
            }
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
                .fillMaxWidth(0.95f)
                .fillMaxHeight(0.9f),
            shape = RoundedCornerShape(16.dp),
            color = Color.Black
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {
                // Header
                Text(
                    text = "SHARE APP!!",
                    color = Color.Yellow,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                // Task Description
                Text(
                    text = "COMPLETE THE FOLLOWING 2 TASKS AND CLAIM 1 PREMIUM FULL BOOK SUMMARY OF YOUR OWN CHOICE!!",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                // Task Buttons
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    TaskButton(
                        text = "TASK 1",
                        isSelected = currentTask == 0,
                        isCompleted = task1Completed,
                        onClick = { currentTask = 0 }
                    )
                    TaskButton(
                        text = "TASK 2",
                        isSelected = currentTask == 1,
                        isCompleted = task2Completed,
                        onClick = { currentTask = 1 }
                    )
                }

                // Task Content
                when (currentTask) {
                    0 -> {
                        // Task 1 Content
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "SUGGEST/SHARE TREE WITH 2 PEOPLE & ATTACH THE SCREENSHOT HERE!!",
                                color = Color.White,
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )

                            // Share Button
                            Button(
                                onClick = {
                                    // Share intent will be implemented here
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Yellow
                                ),
                                modifier = Modifier.padding(bottom = 16.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Share,
                                    contentDescription = "Share",
                                    tint = Color.Black
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "APP LINK",
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            // Upload Screenshots
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                repeat(2) { index ->
                                    UploadButton(
                                        isUploaded = screenshotsUploaded > index,
                                        onClick = { imagePickerLauncher.launch("image/*") }
                                    )
                                }
                            }
                        }
                    }
                    1 -> {
                        // Task 2 Content
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "WATCH 5 ADS!!",
                                color = Color.White,
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )

                            // Ad Progress
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                repeat(5) { index ->
                                    AdProgressIndicator(
                                        isWatched = adsWatched > index,
                                        onClick = {
                                            // Ad watching logic will be implemented here
                                            if (adsWatched <= index) {
                                                adsWatched++
                                                if (adsWatched >= 5) {
                                                    task2Completed = true
                                                }
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                // Complete Task Text
                Text(
                    text = "COMPLETE TASK AND CLAIM REWARD!!",
                    color = Color.Yellow,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                // Close Button
                TextButton(
                    onClick = onDismiss,
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(top = 16.dp),
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
private fun TaskButton(
    text: String,
    isSelected: Boolean,
    isCompleted: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) Color.Yellow else Color.DarkGray)
            .clickable(onClick = onClick)
            .padding(horizontal = 24.dp, vertical = 12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = text,
                color = if (isSelected) Color.Black else Color.White,
                fontWeight = FontWeight.Bold
            )
            if (isCompleted) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Completed",
                    tint = Color.Green
                )
            }
        }
    }
}

@Composable
private fun UploadButton(
    isUploaded: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(80.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.DarkGray)
            .border(2.dp, if (isUploaded) Color.Green else Color.Yellow, RoundedCornerShape(8.dp))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        if (isUploaded) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "Uploaded",
                tint = Color.Green,
                modifier = Modifier.size(32.dp)
            )
        } else {
            Text(
                text = "ATTACH\nHERE",
                color = Color.Yellow,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun AdProgressIndicator(
    isWatched: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(if (isWatched) Color.Green else Color.DarkGray)
            .border(2.dp, Color.Yellow, CircleShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "TAP",
            color = if (isWatched) Color.Black else Color.Yellow,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
