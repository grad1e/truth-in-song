package dev.rtrilia.truthinsong.ui.splash

import androidx.lifecycle.ViewModel
import dev.rtrilia.truthinsong.repository.Repository

class SplashViewModel(private val repository: Repository) : ViewModel() {
    fun getDbRows() = repository.getDbRows()
}