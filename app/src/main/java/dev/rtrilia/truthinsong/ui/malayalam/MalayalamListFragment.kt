package dev.rtrilia.truthinsong.ui.malayalam

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
import dev.rtrilia.truthinsong.databinding.FragmentMalayalamListBinding

/**
 * A simple [Fragment] subclass.
 */
@AndroidEntryPoint
class MalayalamListFragment : Fragment(R.layout.fragment_malayalam_list) {

    private val binding by viewBinding(FragmentMalayalamListBinding::bind)
    private val viewModel by viewModels<MalayalamListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val adapter = MalayalamListAdapter {
            it.id?.let { id ->
                findNavController().navigate(
                    MalayalamListFragmentDirections.actionGlobalSongFragment(
                        id
                    )
                )
            }
        }
        binding.rvMalayalamList.adapter = adapter
        adapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        binding.rvMalayalamList.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                RecyclerView.VERTICAL
            )
        )
        binding.rvMalayalamList.setHasFixedSize(true)

        viewModel.getMalayalamList().observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
    }


    override fun onDestroyView() {
        binding.rvMalayalamList.adapter = null
        super.onDestroyView()
    }

}
