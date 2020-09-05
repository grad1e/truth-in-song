package dev.rtrilia.truthinsong.ui.responsive

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.paging.toLiveData
import dev.rtrilia.truthinsong.repository.Repository

class ResponsiveListViewModel @ViewModelInject constructor(private val repository: Repository) : ViewModel() {

    fun getResponsiveList() =
        repository.getResponsiveList().toLiveData(pageSize = 25)

}