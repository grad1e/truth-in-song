package dev.rtrilia.truthinsong.ui.song

import androidx.lifecycle.ViewModel
import dev.rtrilia.truthinsong.repository.Repository

class SongViewModel(id: String, repository: Repository) : ViewModel() {
    val song = repository.getSong(id)
}