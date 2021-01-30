package dev.rtrilia.truthinsong.ui.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rtrilia.truthinsong.repository.Repository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    var uiMode
        get() = repository.uiMode
        set(value) {
            repository.uiMode = value
        }

    var shuffleMode
        get() = repository.shuffleMode
        set(value) {
            repository.shuffleMode = value
        }

}

