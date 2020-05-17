package dev.rtrilia.truthinsong.ui.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import dev.rtrilia.truthinsong.database.SongDatabase
import dev.rtrilia.truthinsong.repository.Repository

class SearchListViewModel(private val repository: Repository) : ViewModel() {

    var searchString = MutableLiveData<String>()

    fun getSearchList() =
        Transformations.switchMap(searchString) {
            repository.getSearchList(it, it, "%$it%")
        }


}