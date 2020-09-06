package dev.rtrilia.truthinsong.ui.song

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import dev.rtrilia.truthinsong.R
import dev.rtrilia.truthinsong.databinding.FragmentSongBinding
import dev.rtrilia.truthinsong.ui.home.HomeActivity

@AndroidEntryPoint
class SongFragment : Fragment() {

    private lateinit var binding: FragmentSongBinding
    private val viewModel by viewModels<SongViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSongBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
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
        viewModel.songId.observe(viewLifecycleOwner) {
            it?.let { songId ->
                (activity as HomeActivity).supportActionBar?.title = songId
            }
        }

        viewModel.songAuthor.observe(viewLifecycleOwner) {
            it?.let { author ->
                if (author.isBlank()) {
                    binding.songAuthor.visibility = View.GONE
                }
            }
        }
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_fragment_song, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.font_size_decrease -> {
                binding.songNumber.setTextSize(0, binding.songNumber.textSize - 4.0f)
                binding.songTitle.setTextSize(0, binding.songTitle.textSize - 4.0f)
                binding.songContent.setTextSize(0, binding.songContent.textSize - 4.0f)
                binding.songAuthor.setTextSize(0, binding.songAuthor.textSize - 4.0f)
                return true
            }
            R.id.font_size_increase -> {
                binding.songNumber.setTextSize(0, binding.songNumber.textSize + 4.0f)
                binding.songTitle.setTextSize(0, binding.songTitle.textSize + 4.0f)
                binding.songContent.setTextSize(0, binding.songContent.textSize + 4.0f)
                binding.songAuthor.setTextSize(0, binding.songAuthor.textSize + 4.0f)
                return true
            }
            R.id.share -> {
                val songTitle = binding.songTitle.text
                val songContent = binding.songContent.text
                val shareString = resources.getString(R.string.share_text, songTitle, songContent)
                val sendIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, shareString)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }


}
