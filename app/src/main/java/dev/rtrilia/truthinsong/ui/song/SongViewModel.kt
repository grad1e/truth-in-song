package dev.rtrilia.truthinsong.ui.song

import androidx.lifecycle.ViewModel
import dev.rtrilia.truthinsong.repository.Repository

class SongViewModel(val id: String, val repository: Repository) : ViewModel() {
    fun getSong() = repository.getSong(id)
}