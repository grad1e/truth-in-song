package dev.rtrilia.truthinsong.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.rtrilia.truthinsong.repository.Repository

class SearchListViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchListViewModel::class.java)) {
            return SearchListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}