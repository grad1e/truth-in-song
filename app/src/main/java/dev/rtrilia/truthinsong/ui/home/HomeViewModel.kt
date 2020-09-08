package dev.rtrilia.truthinsong.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import dev.rtrilia.truthinsong.repository.Repository

class HomeViewModel @ViewModelInject constructor(val repository: Repository) : ViewModel() {
    fun getUiMode(): Int = repository.getUiModePref()
    fun setUiMode(value: Int) = repository.setUiModePref(value)
}

