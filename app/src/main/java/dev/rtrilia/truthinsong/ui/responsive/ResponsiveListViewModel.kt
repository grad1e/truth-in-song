package dev.rtrilia.truthinsong.ui.responsive

import androidx.lifecycle.ViewModel
import androidx.paging.toLiveData
import dev.rtrilia.truthinsong.repository.Repository

class ResponsiveListViewModel(private val repository: Repository) : ViewModel() {

    fun getResponsiveList() =
        repository.getResponsiveList().toLiveData(pageSize = 25)

}