package dev.rtrilia.truthinsong.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dev.rtrilia.truthinsong.SongApplication
import dev.rtrilia.truthinsong.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentSplashBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        val repository = (requireActivity().application as SongApplication).getRepository()
        val viewModel: SplashViewModel by viewModels { SplashViewModelFactory(repository) }

        viewModel.getDbRows().observe(viewLifecycleOwner, Observer {
            if (it == 1624) {
                navController.navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
            }
        })
    }

}


