package dev.rtrilia.truthinsong.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import dev.rtrilia.truthinsong.SongApplication
import dev.rtrilia.truthinsong.databinding.ActivitySplashBinding
import dev.rtrilia.truthinsong.ui.home.HomeActivity
import kotlinx.coroutines.delay

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = (application as SongApplication).getRepository()
        val viewModel: SplashActivityViewModel by viewModels { SplashActivityViewModelFactory(repository) }
        binding.lifecycleOwner = this

        viewModel.getDbRows().observe(this, Observer {
            if (it == 1624) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        })

    }
}


