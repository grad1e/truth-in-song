package dev.rtrilia.truthinsong.ui.song

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import dev.rtrilia.truthinsong.database.SongDatabase
import dev.rtrilia.truthinsong.models.Song
import dev.rtrilia.truthinsong.repository.Repository

class SongFragmentViewModel(id: String, application: Application) : AndroidViewModel(application) {
    private val songBookDao = SongDatabase.getDatabase(application).songBookDao
    private val repository = Repository(songBookDao)

    val song= repository.getSong(id)

}