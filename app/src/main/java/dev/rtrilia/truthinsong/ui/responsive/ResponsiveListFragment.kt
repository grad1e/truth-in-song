package dev.rtrilia.truthinsong.ui.responsive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import dev.rtrilia.truthinsong.databinding.FragmentResponsiveListBinding

/**
 * A simple [Fragment] subclass.
 */
@AndroidEntryPoint
class ResponsiveListFragment : Fragment(R.layout.fragment_responsive_list) {

    private val binding by viewBinding(FragmentResponsiveListBinding::bind)
    private val viewModel by viewModels<ResponsiveListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val adapter = ResponsiveListAdapter {
            it.id?.let { id ->
                findNavController().navigate(
                    ResponsiveListFragmentDirections.actionGlobalSongFragment(
                        id
                    )
                )
            }
        }
        binding.rvScripturalList.adapter = adapter
        adapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        binding.rvScripturalList.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                RecyclerView.VERTICAL
            )
        )
        binding.rvScripturalList.setHasFixedSize(true)

        viewModel.getResponsiveList().observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    override fun onDestroyView() {
        binding.rvScripturalList.adapter = null
        super.onDestroyView()
    }

}
