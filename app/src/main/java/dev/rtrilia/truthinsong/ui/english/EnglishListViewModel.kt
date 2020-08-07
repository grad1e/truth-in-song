package dev.rtrilia.truthinsong.ui.english

import androidx.lifecycle.ViewModel
import androidx.paging.toLiveData
import dev.rtrilia.truthinsong.repository.Repository

class EnglishListViewModel(private val repository: Repository) : ViewModel() {

    fun getEnglishList() =
        repository.getEnglishList().toLiveData(pageSize = 25)

}