package dev.rtrilia.truthinsong.ui.english

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import dev.rtrilia.truthinsong.database.SongDatabase
import dev.rtrilia.truthinsong.models.EnglishList
import dev.rtrilia.truthinsong.repository.Repository

class EnglishListFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val songBookDao = SongDatabase.getDatabase(application).songBookDao
    private val repository = Repository(songBookDao)

    val englishListList: LiveData<PagedList<EnglishList>>
    
    init {
        englishListList = repository.getEnglishList().toLiveData(pageSize = 50)
    }
}