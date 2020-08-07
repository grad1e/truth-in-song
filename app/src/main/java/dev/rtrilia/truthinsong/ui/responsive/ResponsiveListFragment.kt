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
import dev.rtrilia.truthinsong.SongApplication
import dev.rtrilia.truthinsong.databinding.FragmentResponsiveListBinding

/**
 * A simple [Fragment] subclass.
 */
class ResponsiveListFragment : Fragment() {

    private lateinit var binding: FragmentResponsiveListBinding
    private lateinit var factory: ResponsiveListViewModelFactory
    private val viewModel by viewModels<ResponsiveListViewModel>({ this }, { factory })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentResponsiveListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        val repository = (activity?.application as SongApplication).getRepository()
        factory = ResponsiveListViewModelFactory(repository)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val adapter = ResponsiveListAdapter(ResponsiveListItemListener {
            findNavController().navigate(ResponsiveListFragmentDirections.actionGlobalDetailFragment(it))
        })
        binding.rvScripturalList.adapter = adapter
        adapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        binding.rvScripturalList.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
        binding.rvScripturalList.setHasFixedSize(true)

        viewModel.getResponsiveList().observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }


}
