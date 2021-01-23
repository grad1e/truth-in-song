package dev.rtrilia.truthinsong.ui.english

import androidx.lifecycle.ViewModel
import androidx.paging.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rtrilia.truthinsong.repository.Repository
import javax.inject.Inject

@HiltViewModel
class EnglishListViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    fun getEnglishList() =
        repository.getEnglishList().toLiveData(pageSize = 25)

}