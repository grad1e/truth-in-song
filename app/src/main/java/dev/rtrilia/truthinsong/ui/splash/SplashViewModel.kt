package dev.rtrilia.truthinsong.ui.splash

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import dev.rtrilia.truthinsong.repository.Repository

class SplashViewModel @ViewModelInject constructor(private val repository: Repository) : ViewModel() {
    fun getDbRows() = repository.getDbRows()
}