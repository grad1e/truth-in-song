package dev.rtrilia.truthinsong.ui.song

import android.app.Application
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import dev.rtrilia.truthinsong.R
import dev.rtrilia.truthinsong.databinding.FragmentSongBinding

/**
 * A simple [Fragment] subclass.
 */
class SongFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentSongBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner


        val id = SongFragmentArgs.fromBundle(requireArguments()).id
        val viewModel = ViewModelProvider(this, SongFragmentViewModelFactory(id, Application())).get(SongFragmentViewModel::class.java)

        val navController = NavHostFragment.findNavController(this)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration)

        viewModel.song.observe(viewLifecycleOwner, Observer {
            val str = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(it.content, FROM_HTML_MODE_LEGACY)
            } else {
                Html.fromHtml(it.content)
            }
            binding.songContent.text = str

            if ((it.author == null) || (it.author == "")) {
                binding.songAuthor.visibility = View.GONE
            }

            binding.song = it
        })

        binding.toolbar.inflateMenu(R.menu.menu_fragment_song)
        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_font_decrease -> {
                    binding.songNumber.setTextSize(0, binding.songNumber.textSize - 4.0f)
                    binding.songTitle.setTextSize(0, binding.songTitle.textSize - 4.0f)
                    binding.songContent.setTextSize(0, binding.songContent.textSize - 4.0f)
                    binding.songAuthor.setTextSize(0, binding.songAuthor.textSize - 4.0f)
                    return@setOnMenuItemClickListener true
                }
                R.id.menu_font_increase -> {
                    binding.songNumber.setTextSize(0, binding.songNumber.textSize + 4.0f)
                    binding.songTitle.setTextSize(0, binding.songTitle.textSize + 4.0f)
                    binding.songContent.setTextSize(0, binding.songContent.textSize + 4.0f)
                    binding.songAuthor.setTextSize(0, binding.songAuthor.textSize + 4.0f)
                    return@setOnMenuItemClickListener true
                }
                else -> return@setOnMenuItemClickListener false
            }
        }

        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        return binding.root
    }


}
