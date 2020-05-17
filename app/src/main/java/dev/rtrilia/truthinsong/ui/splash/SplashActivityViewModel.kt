package dev.rtrilia.truthinsong.ui.splash

import androidx.lifecycle.ViewModel
import dev.rtrilia.truthinsong.repository.Repository

class SplashActivityViewModel(private val repository: Repository) : ViewModel() {
    fun getDbRows() = repository.getDbRows()
}