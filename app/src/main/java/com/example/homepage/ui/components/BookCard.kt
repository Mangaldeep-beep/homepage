package com.example.homepage.ui.components

import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import coil.compose.AsyncImage
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import com.example.homepage.R

@Composable
fun BookCard(
    title: String,
    author: String,
    imageUrl: String = "",
    imageRes: Int = R.drawable.book_placeholder,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Column {
            if (imageUrl.isNotEmpty()) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "$title by $author",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.book_placeholder),
                    error = painterResource(id = R.drawable.book_placeholder)
                )
            } else {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = "$title by $author",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Text(
                text = title,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
            Text(
                text = author,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewBookCard() {
    BookCard(
        title = "Sample Book",
        author = "Author Name"
    )
}
