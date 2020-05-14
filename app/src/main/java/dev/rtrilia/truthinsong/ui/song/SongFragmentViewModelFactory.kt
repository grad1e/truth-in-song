package dev.rtrilia.truthinsong.ui.song

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class SongFragmentViewModelFactory(private val id: String, private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SongFragmentViewModel::class.java)) {
            return SongFragmentViewModel(id,application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}