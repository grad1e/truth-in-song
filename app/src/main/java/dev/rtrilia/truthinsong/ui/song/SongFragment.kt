package dev.rtrilia.truthinsong.ui.song

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import dev.rtrilia.truthinsong.R
import dev.rtrilia.truthinsong.SongApplication
import dev.rtrilia.truthinsong.databinding.FragmentSongBinding
import dev.rtrilia.truthinsong.ui.home.HomeActivity

class SongFragment : Fragment() {

    private lateinit var binding: FragmentSongBinding
    private lateinit var factory: SongViewModelFactory
    private val viewModel by viewModels<SongViewModel>({ this }, { factory })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSongBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        val repository = (activity?.application as SongApplication).getRepository()
        factory = SongViewModelFactory(repository)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        val args by navArgs<SongFragmentArgs>()
        viewModel.getSong(args.id)
        setSongData()
    }

    private fun setSongData() {
        viewModel.songId.observe(viewLifecycleOwner, Observer {
            it?.let { songId ->
                (activity as HomeActivity).setToolbarTitle(songId)
            }
        })

        viewModel.songAuthor.observe(viewLifecycleOwner, Observer {
            it?.let { author ->
                if (author.isBlank()) {
                    binding.songAuthor.visibility = View.GONE
                }
            }
        })
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_fragment_song, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_font_decrease -> {
                binding.songNumber.setTextSize(0, binding.songNumber.textSize - 4.0f)
                binding.songTitle.setTextSize(0, binding.songTitle.textSize - 4.0f)
                binding.songContent.setTextSize(0, binding.songContent.textSize - 4.0f)
                binding.songAuthor.setTextSize(0, binding.songAuthor.textSize - 4.0f)
                return true
            }
            R.id.menu_font_increase -> {
                binding.songNumber.setTextSize(0, binding.songNumber.textSize + 4.0f)
                binding.songTitle.setTextSize(0, binding.songTitle.textSize + 4.0f)
                binding.songContent.setTextSize(0, binding.songContent.textSize + 4.0f)
                binding.songAuthor.setTextSize(0, binding.songAuthor.textSize + 4.0f)
                return true
            }
            else -> return super.onOptionsItemSelected(item)

        }
    }


}
