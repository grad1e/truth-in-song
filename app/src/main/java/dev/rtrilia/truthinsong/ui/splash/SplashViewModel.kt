package dev.rtrilia.truthinsong.ui.splash

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

    fun getDbRows() = repository.getDbRows()

    fun getUiMode() = repository.getUiModePref()

    fun setDbRowsCheckSuccess() {
        _onDbRowsCheckSuccess.tryEmit(true)
    }

}