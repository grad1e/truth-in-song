package dev.rtrilia.truthinsong.ui.responsive

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.rtrilia.truthinsong.repository.Repository

@Suppress("UNCHECKED_CAST")
class ResponsiveListViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResponsiveListViewModel::class.java)) {
            return ResponsiveListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}