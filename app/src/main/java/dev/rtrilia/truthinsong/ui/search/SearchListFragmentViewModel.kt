package dev.rtrilia.truthinsong.ui.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import dev.rtrilia.truthinsong.database.SongDatabase
import dev.rtrilia.truthinsong.repository.Repository

class SearchListFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val songBookDao = SongDatabase.getDatabase(application).songBookDao
    private val repository = Repository(songBookDao)

    var searchString = MutableLiveData<String>()
    val searchList = Transformations.switchMap(searchString) {
        repository.getSearchList(it, it, "%$it%")
    }


}