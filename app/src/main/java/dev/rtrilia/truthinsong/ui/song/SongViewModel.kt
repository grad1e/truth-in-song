package dev.rtrilia.truthinsong.ui.song

import androidx.lifecycle.ViewModel
import dev.rtrilia.truthinsong.repository.Repository

class SongViewModel(val repository: Repository) : ViewModel() {
    fun getSong(id: String) = repository.getSong(id)
}