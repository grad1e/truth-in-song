package dev.rtrilia.truthinsong.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.rtrilia.truthinsong.repository.Repository

class SplashActivityViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashActivityViewModel::class.java)) {
            return SplashActivityViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}