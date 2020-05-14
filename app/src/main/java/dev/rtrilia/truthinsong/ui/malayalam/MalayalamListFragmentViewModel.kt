package dev.rtrilia.truthinsong.ui.malayalam

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.toLiveData
import dev.rtrilia.truthinsong.database.SongDatabase
import dev.rtrilia.truthinsong.repository.Repository

class MalayalamListFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val songBookDao = SongDatabase.getDatabase(application).songBookDao
    private val repository = Repository(songBookDao)

    val malayalamList = repository.getMalayalamList().toLiveData(pageSize = 50)


}