package com.example.homepage.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ContentSection(
    items: List<ContentItem>,
    modifier: Modifier = Modifier,
    title: String? = null
) {
    Column(modifier = modifier.padding(vertical = 8.dp)) {
        if (title != null) {
            SectionHeader(
                title = title,
                onSeeAllClick = { /* Handle see all click */ }
            )
        }
        
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(items) { item ->
                ContentItem(
                    item = item,
                    onClick = { /* Handle item click */ },
                    onDownloadClick = { /* Handle download click */ }
                )
            }
        }
    }
} 