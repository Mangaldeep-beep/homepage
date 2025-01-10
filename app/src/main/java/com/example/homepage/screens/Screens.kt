package com.example.homepage.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homepage.R
import com.example.homepage.ui.components.BookCard

data class Book(
    val title: String,
    val author: String,
    val imageRes: Int = R.drawable.book_placeholder,
    val onClick: () -> Unit = {}
)

val popularEBooks = listOf(
    Book("Dune", "Frank Herbert"),
    Book("The Hobbit", "J.R.R. Tolkien"),
    Book("Project Hail Mary", "Andy Weir"),
    Book("The Way of Kings", "Brandon Sanderson"),
    Book("Neuromancer", "William Gibson")
)

val popularAudiobooks = listOf(
    Book(
        "The Shadows of Whitechapel",
        "Victoria Thompson",
        R.drawable.ic_whitechapel
    ),
    Book(
        "The Haunted Boleskine House",
        "Lon Milo DuQuette",
        R.drawable.ic_boleskine
    ),
    Book(
        "Conspiracy of Bollywood",
        "Stacey Halls",
        R.drawable.ic_bollywood
    ),
    Book(
        "The Exorcism of Anneliese Michel",
        "Felicitas D. Goodman",
        R.drawable.ic_anneliese
    )
)

val popularPodcasts = listOf(
    Book("Serial", "Sarah Koenig"),
    Book("This American Life", "Ira Glass"),
    Book("RadioLab", "Jad Abumrad"),
    Book("Reply All", "Alex Goldman"),
    Book("99% Invisible", "Roman Mars")
)

@Composable
fun BookSection(
    title: String,
    books: List<Book>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        LazyRow {
            items(books) { book ->
                BookCard(
                    title = book.title,
                    author = book.author,
                    imageRes = book.imageRes,
                    onClick = book.onClick
                )
            }
        }
    }
}

@Composable
fun AudiobookScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        BookSection(
            title = "Popular Audiobooks",
            books = popularAudiobooks
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        BookSection(
            title = "New Releases",
            books = popularAudiobooks.reversed()
        )
    }
}

@Composable
fun EBookScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        BookSection(
            title = "Popular E-Books",
            books = popularEBooks
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        BookSection(
            title = "New Releases",
            books = popularEBooks.reversed()
        )
    }
}

@Composable
fun PodcastScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        BookSection(
            title = "Popular Podcasts",
            books = popularPodcasts
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        BookSection(
            title = "New Episodes",
            books = popularPodcasts.reversed()
        )
    }
}

@Composable
fun LibraryScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        BookSection(
            title = "Your Books",
            books = popularEBooks + popularAudiobooks
        )
    }
}