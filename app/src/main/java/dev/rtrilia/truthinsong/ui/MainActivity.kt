package dev.rtrilia.truthinsong.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.rtrilia.truthinsong.R
import dev.rtrilia.truthinsong.databinding.ActivityMainBinding
import dev.rtrilia.truthinsong.util.hide
import dev.rtrilia.truthinsong.util.setCustomClickListener
import dev.rtrilia.truthinsong.util.show

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigationItems()
        setupEdgeToEdge()
        binding.fabSearch.setCustomClickListener {
            openSearch()
        }
    }

    private fun setupNavigationItems() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        setSupportActionBar(binding.homeToolbar)
        val appBarConfiguration =
            AppBarConfiguration.Builder(R.id.homeFragment).build()
        binding.homeToolbar.setupWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> setViewsVisible()
                R.id.songFragment -> setViewsVisible()
            }
        }
    }

    private fun setViewsGone() {
        binding.homeToolbar.hide()
        binding.fabSearch.hide()
    }

    private fun setViewsVisible() {
        binding.homeToolbar.show()
        binding.fabSearch.show()
    }

    private fun openSearch() {
        navController.navigate(R.id.action_global_searchFragment)
    }

    private fun setupEdgeToEdge() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

}