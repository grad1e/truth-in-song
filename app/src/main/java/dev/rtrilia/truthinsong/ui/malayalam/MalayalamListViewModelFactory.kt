package dev.rtrilia.truthinsong.ui.malayalam

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.rtrilia.truthinsong.repository.Repository

@Suppress("UNCHECKED_CAST")
class MalayalamListViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MalayalamListViewModel::class.java)) {
            return MalayalamListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}