package dev.rtrilia.truthinsong.ui.malayalam

import androidx.lifecycle.ViewModel
import androidx.paging.toLiveData
import dev.rtrilia.truthinsong.repository.Repository

class MalayalamListViewModel(private val repository: Repository) : ViewModel() {

    fun getMalayalamList() =
        repository.getMalayalamList().toLiveData(pageSize = 25)

}