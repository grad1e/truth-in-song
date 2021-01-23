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
import dagger.hilt.android.AndroidEntryPoint
import dev.rtrilia.truthinsong.databinding.FragmentResponsiveListBinding

/**
 * A simple [Fragment] subclass.
 */
@AndroidEntryPoint
class ResponsiveListFragment : Fragment() {

    private lateinit var binding: FragmentResponsiveListBinding
    private val viewModel by viewModels<ResponsiveListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResponsiveListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val adapter = ResponsiveListAdapter {
            it.id?.let { id ->
                findNavController().navigate(
                    ResponsiveListFragmentDirections.actionGlobalDetailFragment(
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
