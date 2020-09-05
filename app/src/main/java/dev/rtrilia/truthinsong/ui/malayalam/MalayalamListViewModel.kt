package dev.rtrilia.truthinsong.ui.malayalam

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.paging.toLiveData
import dev.rtrilia.truthinsong.repository.Repository

class MalayalamListViewModel @ViewModelInject constructor(private val repository: Repository) : ViewModel() {

    fun getMalayalamList() =
        repository.getMalayalamList().toLiveData(pageSize = 25)

}