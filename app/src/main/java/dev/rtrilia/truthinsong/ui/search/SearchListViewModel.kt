package dev.rtrilia.truthinsong.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rtrilia.truthinsong.repository.Repository
import javax.inject.Inject

@HiltViewModel
class SearchListViewModel @Inject constructor(private val repository: Repository) :
    ViewModel() {

    val searchString = MutableLiveData<String>()

    fun getSearchList() =
        Transformations.switchMap(searchString) {
            repository.getSearchList(it, it, "%$it%")
        }


}