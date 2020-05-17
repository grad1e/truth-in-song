package dev.rtrilia.truthinsong.ui.english

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.rtrilia.truthinsong.repository.Repository

@Suppress("UNCHECKED_CAST")
class EnglishListViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EnglishListViewModel::class.java)) {
            return EnglishListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}