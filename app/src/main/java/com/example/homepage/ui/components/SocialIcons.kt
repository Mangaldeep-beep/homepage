package com.example.homepage.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.*
import androidx.compose.ui.graphics.vector.ImageVector

object SocialIcons {
    // Social Media Icons
    val YouTube = Icons.Default.PlayCircle
    val Instagram = Icons.Default.PhotoCamera
    val Facebook = Icons.Default.Share
    val Telegram = Icons.Default.Send
    val LinkedIn = Icons.Default.Business
    val Twitter = Icons.Default.Tag
    val WhatsApp = Icons.Default.Chat
    
    // Custom Website Icon - using headphones icon to represent music website
    val Website = Icons.Default.Headphones

    // Function to get social icon
    fun getIcon(platformName: String): ImageVector {
        return when (platformName) {
            "YouTube" -> YouTube
            "Instagram" -> Instagram
            "Facebook" -> Facebook
            "Telegram" -> Telegram
            "LinkedIn" -> LinkedIn
            "Our Website" -> Website
            "X" -> Twitter
            "WhatsApp" -> WhatsApp
            else -> Icons.Default.Link
        }
    }
}
