package dev.rtrilia.truthinsong.ui.splash

import android.app.Application
import android.content.res.Resources
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import dev.rtrilia.truthinsong.R
import dev.rtrilia.truthinsong.database.SongDatabase
import dev.rtrilia.truthinsong.models.toDatabase.*
import dev.rtrilia.truthinsong.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class SplashActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val songBookDao = SongDatabase.getDatabase(application).songBookDao
    private val repository = Repository(songBookDao)


    private val _isDbPresent = MutableLiveData<Boolean>()
    val isDbPresent: LiveData<Boolean>
        get() = _isDbPresent

    init {
        checkDbData(application)
    }


    private fun insertTopics(resources: Resources) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val jsonString = resources.openRawResource(R.raw.topics).bufferedReader().use {
                    it.readText()
                }
                val moshi = Moshi.Builder().build()
                val adapter: JsonAdapter<Topics> = moshi.adapter(Topics::class.java)
                val topicObject = adapter.fromJson(jsonString)
                repository.insertTopic(topicObject!!.asDatabaseModel())
            }
        }
        Timber.d("Inserted Topics")
    }

    private fun insertEnglish(resources: Resources) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val jsonString = resources.openRawResource(R.raw.english).bufferedReader().use {
                    it.readText()
                }
                val moshi = Moshi.Builder().build()
                val adapter: JsonAdapter<English> = moshi.adapter(English::class.java)
                val englishObject = adapter.fromJson(jsonString)
                repository.insertEnglish(englishObject!!.asDatabaseModel())
            }
        }
        Timber.d("Inserted English")
    }

    private fun insertScriptural(resources: Resources) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val jsonString = resources.openRawResource(R.raw.responsive).bufferedReader().use {
                    it.readText()
                }
                val moshi = Moshi.Builder().build()
                val adapter: JsonAdapter<Responsive> = moshi.adapter(Responsive::class.java)
                val scripturalObject = adapter.fromJson(jsonString)
                repository.insertResponsive(scripturalObject!!.asDatabaseModel())
            }
        }
        Timber.d("Inserted Responsive")
    }

    private fun insertMalayalam(resources: Resources) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val jsonString = resources.openRawResource(R.raw.malayalam).bufferedReader().use {
                    it.readText()
                }
                val moshi = Moshi.Builder().build()
                val adapter: JsonAdapter<Malayalam> = moshi.adapter(Malayalam::class.java)
                val malayalamObject = adapter.fromJson(jsonString)
                repository.insertMalayalam(malayalamObject!!.asDatabaseModel())
            }
        }
        Timber.d("Inserted Malayalam")
    }

    private fun checkDbData(application: Application) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                SongDatabase.getDatabase(application).clearAllTables()
                /*if (repository.getDbRows() == 1624) {
                    delay(1000)
                    _isDbPresent.postValue(true)
                    Timber.d("Database present")
                } else {
                    Timber.d("Database cleared")*/
                insertTopics(application.resources)
                insertEnglish(application.resources)
                insertScriptural(application.resources)
                insertMalayalam(application.resources)
                delay(1000)
                onDbPopulateFinished()
                //}
            }
        }
    }

    private fun onDbPopulateFinished() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (repository.getDbRows() == 1624) {
                    _isDbPresent.postValue(true)
                    Timber.d("Database present")
                } else {
                    Timber.d("Error loading database")
                }
            }
        }
    }


}