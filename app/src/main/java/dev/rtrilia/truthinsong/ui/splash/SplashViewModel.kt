package dev.rtrilia.truthinsong.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rtrilia.truthinsong.repository.Repository
import dev.rtrilia.truthinsong.util.mutableEventFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _onDbRowsCheckSuccess = mutableEventFlow<Boolean>()
    val onDbRowsCheckSuccess = _onDbRowsCheckSuccess.asSharedFlow()

    private val _versionName = MutableLiveData<String>()
    val versionName: LiveData<String> = _versionName

    fun getDbRows() = repository.getDbRows()

    fun getUiMode() = repository.uiMode

    fun setDbRowsCheckSuccess() {
        _onDbRowsCheckSuccess.tryEmit(true)
    }

    fun setVersionName(versionName: String) {
        _versionName.value = versionName
    }

}