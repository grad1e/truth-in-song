package dev.rtrilia.truthinsong.ui.responsive

import androidx.lifecycle.ViewModel
import androidx.paging.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rtrilia.truthinsong.repository.Repository
import javax.inject.Inject

@HiltViewModel
class ResponsiveListViewModel @Inject constructor(private val repository: Repository) :
    ViewModel() {

    fun getResponsiveList() =
        repository.getResponsiveList().toLiveData(pageSize = 25)

}