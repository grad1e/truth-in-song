package dev.rtrilia.truthinsong.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.rtrilia.truthinsong.R
import dev.rtrilia.truthinsong.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigationItems()
        binding.fabSearch.setOnClickListener {
            openSearch()
        }
    }

    private fun setupNavigationItems() {
        navController = findNavController(R.id.nav_host_fragment)
        setSupportActionBar(binding.homeToolbar)
        val appBarConfiguration = AppBarConfiguration.Builder(R.id.splashFragment, R.id.homeFragment).build()
        binding.homeToolbar.setupWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splashFragment -> setViewsGone()
                R.id.homeFragment -> setViewsVisible()
                R.id.songFragment -> setViewsVisible()
            }
        }

    }

    private fun setViewsGone() {
        binding.homeToolbar.visibility = View.GONE
        binding.fabSearch.visibility = View.GONE
    }

    private fun setViewsVisible() {
        binding.homeToolbar.visibility = View.VISIBLE
        binding.fabSearch.visibility = View.VISIBLE
    }

    fun setToolbarTitle(title: String) {
        binding.homeToolbar.title = title
    }

    private fun openSearch() {
        navController.navigate(R.id.action_global_searchFragment)
    }


}



