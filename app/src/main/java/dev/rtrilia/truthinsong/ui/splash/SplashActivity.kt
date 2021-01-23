package dev.rtrilia.truthinsong.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.WindowCompat
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dev.rtrilia.truthinsong.databinding.ActivitySplashBinding
import dev.rtrilia.truthinsong.ui.MainActivity
import dev.rtrilia.truthinsong.util.Constants.DATABASE_ENTRIES
import dev.rtrilia.truthinsong.util.UiMode
import dev.rtrilia.truthinsong.util.observeOnce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val viewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        setupAppTheme()
        navigateToHome()
        checkDataInDb()
    }

    private fun checkDataInDb() {
        viewModel.getDbRows().observe(this) {
            binding.progressBar.setProgressCompat(it, true)
            if (DATABASE_ENTRIES == it) {
                viewModel.setDbRowsCheckSuccess()
            }
        }
    }


    private fun setupAppTheme() {
        when (viewModel.getUiMode()) {
            UiMode.SYSTEM_DEFAULT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            UiMode.DARK_MODE -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            UiMode.LIGHT_MODE -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun navigateToHome() {
        viewModel.onDbRowsCheckSuccess.asLiveData().observeOnce(this){
            lifecycleScope.launch {
                delay(1000)
                Intent(this@SplashActivity, MainActivity::class.java).apply {
                    startActivity(this)
                    finish()
                }
            }
        }
    }

}


