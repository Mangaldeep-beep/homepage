package com.example.homepage.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homepage.R
import kotlinx.coroutines.delay
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*

@Composable
private fun RainbowText(text: String) {
    val colors = listOf(
        Color.Red,
        Color.Blue,
        Color.Green,
        Color.Yellow,
        Color.Magenta
    )

    var currentColorIndex by remember { mutableStateOf(0) }
    val currentColor by animateColorAsState(
        targetValue = colors[currentColorIndex],
        animationSpec = tween(2000),
        label = "colorAnimation"
    )

    LaunchedEffect(key1 = true) {
        while (true) {
            delay(2000)
            currentColorIndex = (currentColorIndex + 1) % colors.size
        }
    }

    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        color = currentColor,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    onMenuClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    showMenu: Boolean = false
) {
    var showAboutDialog by remember { mutableStateOf(false) }
    var showSuggestionsDialog by remember { mutableStateOf(false) }
    var showComingSoonDialog by remember { mutableStateOf(false) }
    val yellowColor = Color(0xFFFFEB3B)

    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(
                onClick = onMenuClick,
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = yellowColor
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = yellowColor
                )
            }
        },
        title = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                AnimatedHeader(
                    isMenuOpen = showMenu,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        },
        actions = {
            IconButton(
                onClick = onProfileClick,
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = yellowColor
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile",
                    tint = yellowColor
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Black
        )
    )

    if (showMenu) {
        MenuDrawer(
            onAboutUsClick = { showAboutDialog = true },
            onSuggestionsClick = { showSuggestionsDialog = true },
            onComingSoonClick = { showComingSoonDialog = true }
        )
    }

    if (showAboutDialog) {
        AboutUsDialog(
            onDismiss = { showAboutDialog = false }
        )
    }

    if (showSuggestionsDialog) {
        SuggestionsDialog(
            onDismiss = { showSuggestionsDialog = false }
        )
    }

    if (showComingSoonDialog) {
        ComingSoonDialog(
            onDismiss = { showComingSoonDialog = false }
        )
    }
}

@Composable
private fun MenuDrawer(
    onAboutUsClick: () -> Unit = {},
    onSuggestionsClick: () -> Unit = {},
    onComingSoonClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.9f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .background(Color.Black)
                .padding(16.dp)
        ) {
            // Tree Logo at the top
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_tree_logo),
                    contentDescription = "Tree Logo",
                    modifier = Modifier
                        .size(180.dp)
                        .padding(8.dp)
                )
            }

            // Welcome text
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                RainbowText(text = "WELCOME CONNECT WITH US")
            }

            Spacer(modifier = Modifier.height(16.dp))

            RainbowText("JOIN OUR COMMUNITY")

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                SocialIcon(R.drawable.ic_youtube, "YouTube")
                SocialIcon(R.drawable.ic_instagram, "Instagram")
                SocialIcon(R.drawable.ic_facebook, "Facebook")
            }
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                SocialIcon(R.drawable.ic_telegram, "Telegram")
                SocialIcon(R.drawable.ic_linkedin, "LinkedIn")
                SocialIcon(R.drawable.ic_twitter, "X")
            }
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                SocialIcon(R.drawable.ic_whatsapp, "WhatsApp")
                SocialIcon(R.drawable.ic_tree_logo, "Our Website")
            }

            Divider(
                modifier = Modifier.padding(vertical = 8.dp),
                color = Color(0xFFFFEB3B).copy(alpha = 0.2f)
            )
            
            // Menu items
            MenuButton(
                text = "About Us",
                showArrow = true,
                onClick = onAboutUsClick
            )
            MenuButton(
                text = "Your Suggestions",
                showArrow = true,
                onClick = onSuggestionsClick
            )
            MenuButton("SHARE APP", showArrow = true)
            MenuButton("LATEST UPDATES", showArrow = true)
            MenuButton("EXPLORE OTHER APPS", showArrow = true)
            MenuButton(
                text = "COMING SOON",
                showArrow = true,
                onClick = onComingSoonClick
            )
        }
    }
}

@Composable
fun SocialIcon(iconRes: Int, contentDescription: String) {
    IconButton(
        onClick = { /* Handle click */ },
        modifier = Modifier.size(32.dp)
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = contentDescription,
            modifier = Modifier.size(32.dp)
        )
    }
}

@Composable
fun MenuButton(
    text: String,
    showArrow: Boolean = false,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clickable { onClick() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFFFFEB3B),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
        if (showArrow) {
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Navigate",
                tint = Color(0xFFFFEB3B)
            )
        }
    }
}