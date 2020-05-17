package dev.rtrilia.truthinsong.ui.song

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.rtrilia.truthinsong.repository.Repository

@Suppress("UNCHECKED_CAST")
class SongViewModelFactory(private val id: String, private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SongViewModel::class.java)) {
            return SongViewModel(id, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}