package dev.rtrilia.truthinsong.ui.english

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import dev.rtrilia.truthinsong.R
import dev.rtrilia.truthinsong.databinding.FragmentEnglishListBinding

/**
 * A simple [Fragment] subclass.
 */
@AndroidEntryPoint
class EnglishListFragment : Fragment(R.layout.fragment_english_list) {

    private val binding by viewBinding(FragmentEnglishListBinding::bind)
    private val viewModel by viewModels<EnglishListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val adapter = EnglishListAdapter {
            it.id?.let { id ->
                findNavController().navigate(
                    EnglishListFragmentDirections.actionGlobalSongFragment(
                        id
                    )
                )
            }
        }
        binding.rvEnglishList.adapter = adapter
        adapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        binding.rvEnglishList.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                RecyclerView.VERTICAL
            )
        )
        binding.rvEnglishList.setHasFixedSize(true)

        viewModel.getEnglishList().observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
    }

    override fun onDestroyView() {
        binding.rvEnglishList.adapter = null
        super.onDestroyView()
    }


}
