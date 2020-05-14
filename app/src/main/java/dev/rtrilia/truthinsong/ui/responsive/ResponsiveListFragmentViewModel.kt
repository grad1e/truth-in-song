package dev.rtrilia.truthinsong.ui.responsive

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import dev.rtrilia.truthinsong.database.SongDatabase
import dev.rtrilia.truthinsong.models.ResponsiveList
import dev.rtrilia.truthinsong.repository.Repository

class ResponsiveListFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val songBookDao = SongDatabase.getDatabase(application).songBookDao
    private val repository = Repository(songBookDao)

    val responsiveListList: LiveData<PagedList<ResponsiveList>>

    init {
        responsiveListList = repository.getResponsiveList().toLiveData(pageSize = 50)
    }

}