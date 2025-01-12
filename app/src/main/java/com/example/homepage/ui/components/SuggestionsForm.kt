package com.example.homepage.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import java.net.HttpURLConnection
import java.net.URL
import java.io.OutputStreamWriter

@Composable
fun SuggestionsDialog(
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    var userName by remember { mutableStateOf("") }
    var userEmail by remember { mutableStateOf("") }
    var bugReport by remember { mutableStateOf("") }
    var bestFeature by remember { mutableStateOf("") }
    var improvementSuggestion by remember { mutableStateOf("") }
    var recommendation by remember { mutableStateOf("") }
    var nextUpdate by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    var isSubmitting by remember { mutableStateOf(false) }

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
            shape = MaterialTheme.shapes.large,
            color = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Title
                Text(
                    text = "Your Suggestions",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.Yellow,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Form Content
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                ) {
                    FormTextField(
                        value = userName,
                        onValueChange = { userName = it },
                        label = "NAME OF USER"
                    )
                    
                    FormTextField(
                        value = userEmail,
                        onValueChange = { userEmail = it },
                        label = "EMAIL OF USER"
                    )
                    
                    FormTextField(
                        value = bugReport,
                        onValueChange = { bugReport = it },
                        label = "SUBMIT BUGS & ERROR COMPLAINTS"
                    )
                    
                    FormTextField(
                        value = bestFeature,
                        onValueChange = { bestFeature = it },
                        label = "WHAT'S THE BEST SECTION or BEST THING ABOUT TREE?"
                    )
                    
                    FormTextField(
                        value = improvementSuggestion,
                        onValueChange = { improvementSuggestion = it },
                        label = "WHAT SECTION YOU WANT US TO IMPROVE IN TREE?"
                    )
                    
                    FormTextField(
                        value = recommendation,
                        onValueChange = { recommendation = it },
                        label = "WHATS YOUR RECOMMENDATION or ANY POINT YOU WANT US TO SAY ABOUT TREE?"
                    )
                    
                    FormTextField(
                        value = nextUpdate,
                        onValueChange = { nextUpdate = it },
                        label = "WHAT THE NEXT BIG UPDATE DO YOU WANT IN TREE?"
                    )
                }

                // Submit and Close buttons
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextButton(
                        onClick = onDismiss,
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = Color.Yellow
                        )
                    ) {
                        Text("Close", fontWeight = FontWeight.Bold)
                    }

                    Button(
                        onClick = {
                            if (!isSubmitting) {
                                isSubmitting = true
                                scope.launch(Dispatchers.IO) {
                                    try {
                                        // Google Form submission URL
                                        val url = URL("https://docs.google.com/forms/d/e/1FAIpQLSckXbbFdplQxLBLHbBZmu7LQ81QlugdpP0WiVEqW4iVe-Kl1g/formResponse")
                                        
                                        val postData = mapOf(
                                            "entry.1234567890" to userName,
                                            "entry.1234567891" to userEmail,
                                            "entry.1234567892" to bugReport,
                                            "entry.1234567893" to bestFeature,
                                            "entry.1234567894" to improvementSuggestion,
                                            "entry.1234567895" to recommendation,
                                            "entry.1234567896" to nextUpdate
                                        ).map { (key, value) ->
                                            "$key=${URLEncoder.encode(value, StandardCharsets.UTF_8.toString())}"
                                        }.joinToString("&")

                                        val conn = url.openConnection() as HttpURLConnection
                                        conn.requestMethod = "POST"
                                        conn.doOutput = true
                                        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")

                                        OutputStreamWriter(conn.outputStream).use { writer ->
                                            writer.write(postData)
                                        }

                                        val responseCode = conn.responseCode
                                        if (responseCode == HttpURLConnection.HTTP_OK) {
                                            onDismiss()
                                        }
                                    } catch (e: Exception) {
                                        e.printStackTrace()
                                    } finally {
                                        isSubmitting = false
                                    }
                                }
                            }
                        },
                        enabled = !isSubmitting,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Yellow,
                            contentColor = Color.Black,
                            disabledContainerColor = Color.Yellow.copy(alpha = 0.5f)
                        )
                    ) {
                        if (isSubmitting) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                color = Color.Black
                            )
                        } else {
                            Text("Submit", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FormTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = label,
            color = Color.Yellow,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedTextColor = Color.White,
                focusedTextColor = Color.White,
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                cursorColor = Color.Yellow,
                focusedBorderColor = Color.Yellow,
                unfocusedBorderColor = Color.Yellow.copy(alpha = 0.5f)
            )
        )
    }
}
