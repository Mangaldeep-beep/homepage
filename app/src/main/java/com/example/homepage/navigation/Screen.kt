package com.example.homepage.navigation

sealed class Screen(val route: String) {
    object Audiobook : Screen("audiobook")
    object EBook : Screen("ebook")
    object Podcast : Screen("podcast")
    object Library : Screen("library")
    object AboutUs : Screen("about_us")
}