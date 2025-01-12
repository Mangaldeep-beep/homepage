package com.example.homepage.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homepage.data.UpdateItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class UpdatesViewModel : ViewModel() {
    private val _updates = MutableStateFlow<List<UpdateItem>>(emptyList())
    val updates: StateFlow<List<UpdateItem>> = _updates
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        fetchUpdates()
        startAutoUpdateCheck()
    }

    private fun startAutoUpdateCheck() {
        viewModelScope.launch {
            while (true) {
                kotlinx.coroutines.delay(15 * 60 * 1000) // Check every 15 minutes
                fetchUpdates()
            }
        }
    }

    fun fetchUpdates() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                
                // Simulated data instead of Firebase
                val currentUpdates = listOf(
                    UpdateItem(
                        version = "Version 1.1.0",
                        date = "January 10, 2025",
                        features = listOf(
                            "Added new audiobook player with enhanced controls",
                            "Improved podcast streaming quality",
                            "New personalized recommendations system",
                            "Dark mode optimization"
                        ),
                        isNew = true
                    ),
                    UpdateItem(
                        version = "Version 1.0.5",
                        date = "December 25, 2024",
                        features = listOf(
                            "Added offline reading mode",
                            "Enhanced search functionality",
                            "Bug fixes and performance improvements",
                            "New reading progress tracking"
                        ),
                        isNew = isNewUpdate("December 25, 2024")
                    ),
                    UpdateItem(
                        version = "Version 1.0.0",
                        date = "December 17, 2024",
                        features = listOf(
                            "Initial release of TREE",
                            "Audiobook player",
                            "E-book reader",
                            "Podcast streaming",
                            "Basic library management"
                        ),
                        isNew = isNewUpdate("December 17, 2024")
                    )
                )

                _updates.value = currentUpdates
            } catch (e: Exception) {
                _error.value = "Failed to fetch updates: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun isNewUpdate(date: String): Boolean {
        try {
            val format = SimpleDateFormat("MMMM dd, yyyy", Locale.US)
            val updateDate = format.parse(date)
            val currentTime = System.currentTimeMillis()
            val threeDaysInMillis = 3 * 24 * 60 * 60 * 1000L
            return updateDate?.let { (currentTime - it.time) <= threeDaysInMillis } ?: false
        } catch (e: Exception) {
            return false
        }
    }
}
