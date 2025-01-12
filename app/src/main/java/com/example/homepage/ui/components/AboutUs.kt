package com.example.homepage.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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

@Composable
fun AboutUsDialog(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        Surface(
            modifier = modifier
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
                    text = "About Us",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.Yellow,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Content
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                ) {
                    // About TREE Section
                    Text(
                        text = "1. About TREE",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Yellow,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    listOf(
                        "TREE is a revolutionary platform designed to bring the best of audiobooks, e-books, and podcasts under one roof. It caters to a wide range of users, offering content that is relatable, engaging, and inspiring.",
                        "TREE is not just an app; it's a space for storytelling, knowledge sharing, and entertainment.",
                        "With a user-friendly interface and diverse content library, TREE ensures that every individual, regardless of their age or preferences, finds something meaningful to enjoy.",
                        "Our goal is to make reading and listening experiences accessible and convenient for everyone, while maintaining top-notch quality and innovation in content delivery.",
                        "TREE reflects our belief that stories have the power to educate, entertain, and connect people across cultures and generations."
                    ).forEach { text ->
                        Text("• $text", modifier = Modifier.padding(bottom = 4.dp))
                    }

                    // Launch Date
                    Text(
                        text = "2. Launched In",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Yellow,
                        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                    )
                    Text("→ Date: December 17, 2025")

                    // Founded Date
                    Text(
                        text = "3. Founded In",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Yellow,
                        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                    )
                    Text("→ Date: April 9, 2024")

                    // Founders
                    Text(
                        text = "4. Founded By",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Yellow,
                        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                    )
                    listOf(
                        Triple("Srija Banik", "CEO / Chairman / Main Founder", "April 9, 2024"),
                        Triple("Aryan Krishna", "CTO / Co-Founder", "July 7, 2024"),
                        Triple("Mangaldeep Pal", "CDO / Co-Founder", "November 15, 2024"),
                        Triple("Swetabja Ghosh", "CFO / Co-Founder", "December 24, 2024")
                    ).forEach { (name, role, date) ->
                        Text(name, fontWeight = FontWeight.Bold)
                        Text("→ Role: $role")
                        Text("→ Joined: $date")
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    // Team Size
                    Text(
                        text = "5. We Are a Team Of:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Yellow,
                        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                    )
                    Text("→ 50+ dedicated members working together to bring TREE to life.")

                    // PAMA Info
                    Text(
                        text = "6. This App Is a Part of PAMA:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Yellow,
                        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                    )
                    Text("→ TREE is the first app launched under PAMA, an innovative app development company founded by Srija Banik.")

                    // About PAMA
                    Text(
                        text = "7. About PAMA:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Yellow,
                        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                    )
                    Text("→ PAMA aims to create a comprehensive ecosystem of apps designed to simplify and enhance people's day-to-day lives.")
                    Text("→ By focusing on innovation, functionality, and user convenience, PAMA is committed to creating products that empower individuals and businesses alike.")

                    // Next App
                    Text(
                        text = "8. Our Next App Is:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Yellow,
                        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                    )
                    Text("→ Details about the next app will be revealed soon!")

                    // About Members
                    Text(
                        text = "About All Members:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Yellow,
                        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                    )
                    Text("→ Details about each team member, their roles, and how they joined us on this exciting journey will be added here.")
                }

                // Close button
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = onDismiss,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.Yellow
                        )
                    ) {
                        Text("Close", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}
