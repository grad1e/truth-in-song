package dev.rtrilia.truthinsong.ui.song

import android.os.Bundle
import android.view.*
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dev.rtrilia.truthinsong.R
import dev.rtrilia.truthinsong.SongApplication
import dev.rtrilia.truthinsong.databinding.FragmentSongBinding
import dev.rtrilia.truthinsong.ui.home.HomeActivity

/**
 * A simple [Fragment] subclass.
 */
class SongFragment : Fragment() {

    private lateinit var binding: FragmentSongBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSongBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = SongFragmentArgs.fromBundle(requireArguments()).id
        val repository = (activity?.application as SongApplication).getRepository()
        val viewModel: SongViewModel by viewModels({ this }, { SongViewModelFactory(id, repository) })

        setHasOptionsMenu(true)

        viewModel.getSong().observe(viewLifecycleOwner, Observer { song ->
            song?.let {
                it.song_id?.let { song_id -> (activity as HomeActivity).setToolbarTitle(song_id) }

                it.content?.let { content ->
                    val str = HtmlCompat.fromHtml(content, HtmlCompat.FROM_HTML_MODE_LEGACY)
                    binding.songContent.text = str
                }

                it.author?.let { author ->
                    if (author.isBlank()) {
                        binding.songAuthor.visibility = View.GONE
                    }
                }
                
                binding.song = it
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
