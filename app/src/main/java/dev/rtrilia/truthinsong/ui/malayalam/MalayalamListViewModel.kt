package dev.rtrilia.truthinsong.ui.malayalam

import androidx.lifecycle.ViewModel
import androidx.paging.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rtrilia.truthinsong.repository.Repository
import javax.inject.Inject

@HiltViewModel
class MalayalamListViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    fun getMalayalamList() =
        repository.getMalayalamList().toLiveData(pageSize = 25)

}