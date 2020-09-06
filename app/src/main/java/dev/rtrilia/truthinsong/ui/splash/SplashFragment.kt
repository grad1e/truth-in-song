package dev.rtrilia.truthinsong.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.rtrilia.truthinsong.databinding.FragmentSplashBinding
import dev.rtrilia.truthinsong.util.UiMode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private val viewModel by viewModels<SplashViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentSplashBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAppTheme()
        lifecycleScope.launch(Dispatchers.Main) {
            delay(1000)
            checkDataInDb()
        }
    }

    private fun checkDataInDb() {
        viewModel.getDbRows().observe(viewLifecycleOwner, Observer {
            Timber.d(it.toString())
            if (it == 1624) {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
            }
        })
    }


    private fun setupAppTheme() {
        when (viewModel.getUiMode()) {
            UiMode.SYSTEM_DEFAULT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            UiMode.DARK_MODE -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            UiMode.LIGHT_MODE -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
    
}


