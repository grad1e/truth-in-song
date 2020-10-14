package dev.rtrilia.truthinsong.ui.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import dev.rtrilia.truthinsong.repository.Repository
import timber.log.Timber

class SearchListViewModel @ViewModelInject constructor(private val repository: Repository) :
    ViewModel() {

    val searchString = MutableLiveData<String>()

    fun getSearchList() =
        Transformations.switchMap(searchString) {
            repository.getSearchList(it, it, "%$it%")
        }


}