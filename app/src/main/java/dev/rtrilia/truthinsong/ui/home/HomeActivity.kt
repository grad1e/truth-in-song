package dev.rtrilia.truthinsong.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import dev.rtrilia.truthinsong.R
import dev.rtrilia.truthinsong.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
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
    }

    fun setToolbarTitle(title: String) {
        binding.homeToolbar.title = title
    }

    private fun openSearch() {
        navController.navigate(R.id.action_global_searchFragment)
    }


}



