package dev.rtrilia.truthinsong.ui.song

import android.text.Spanned
import androidx.core.text.HtmlCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rtrilia.truthinsong.repository.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    private val _songId = MutableLiveData<String?>()
    val songId: LiveData<String?>
        get() = _songId

    private val _songMalTitle = MutableLiveData<String?>()
    val songMalTitle: LiveData<String?>
        get() = _songMalTitle

    private val _songEngTitle = MutableLiveData<String?>()
    val songEngTitle: LiveData<String?>
        get() = _songEngTitle

    private val _songAuthor = MutableLiveData<String?>()
    val songAuthor: LiveData<String?>
        get() = _songAuthor

    private val _songContent = MutableLiveData<Spanned>()
    val songContent: LiveData<Spanned>
        get() = _songContent


    fun getSong(id: String) {
        viewModelScope.launch {
            val song = repository.getSong(id)
            _songId.value = song.song_id
            _songMalTitle.value = song.mal_title
            _songEngTitle.value = song.eng_title
            _songAuthor.value = song.author
            convertHtmlToSpanned(song.content)
        }
    }

    private fun convertHtmlToSpanned(content: String?) {
        content?.let {
            val str = HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY)
            _songContent.value = str
        }
    }

    var fontSize
        get() = repository.fontSize
        set(value) {
            repository.fontSize = value
        }

    var fontSizeSmall
        get() = repository.fontSizeSmall
        set(value) {
            repository.fontSizeSmall = value
        }

    var shuffleMode
        get() = repository.shuffleMode
        set(value) {
            repository.shuffleMode = value
        }
    
}

