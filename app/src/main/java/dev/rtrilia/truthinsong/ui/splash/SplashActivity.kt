package dev.rtrilia.truthinsong.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dev.rtrilia.truthinsong.databinding.ActivitySplashBinding
import dev.rtrilia.truthinsong.ui.home.HomeActivity
import kotlinx.coroutines.delay

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this).get(SplashActivityViewModel::class.java)
        binding.lifecycleOwner = this

        viewModel.isDbPresent.observe(this, Observer {
            if (it) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        })

    }
}
