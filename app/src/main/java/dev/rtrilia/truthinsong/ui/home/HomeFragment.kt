package dev.rtrilia.truthinsong.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import dev.rtrilia.truthinsong.R
import dev.rtrilia.truthinsong.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)

        val navController = NavHostFragment.findNavController(this)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration)

        binding.cardEnglish.setOnClickListener {
            navController.navigate(HomeFragmentDirections.actionHomeFragmentToEnglishListFragment())
        }

        binding.cardMalayalam.setOnClickListener {
            navController.navigate(HomeFragmentDirections.actionHomeFragmentToMalayalamListFragment())
        }

        binding.cardScriptural.setOnClickListener {
            navController.navigate(HomeFragmentDirections.actionHomeFragmentToScripturalListFragment())
        }

        binding.fabSearch.setOnClickListener {
            navController.navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment())
        }

    }




}
