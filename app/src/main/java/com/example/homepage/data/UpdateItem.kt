package com.example.homepage.data

data class UpdateItem(
    val version: String,
    val date: String,
    val features: List<String>,
    val isNew: Boolean = false
)
