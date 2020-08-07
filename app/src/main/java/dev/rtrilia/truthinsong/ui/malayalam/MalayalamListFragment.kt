package dev.rtrilia.truthinsong.ui.malayalam

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
import dev.rtrilia.truthinsong.databinding.FragmentMalayalamListBinding

/**
 * A simple [Fragment] subclass.
 */
class MalayalamListFragment : Fragment() {

    private lateinit var binding: FragmentMalayalamListBinding
    private lateinit var factory: MalayalamListViewModelFactory
    private val viewModel by viewModels<MalayalamListViewModel>({ this }, { factory })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMalayalamListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        val repository = (activity?.application as SongApplication).getRepository()
        factory = MalayalamListViewModelFactory(repository)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val adapter = MalayalamListAdapter(MalayalamListItemClickListener {
            findNavController().navigate(MalayalamListFragmentDirections.actionGlobalDetailFragment(it))
        })
        binding.rvMalayalamList.adapter = adapter
        adapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        binding.rvMalayalamList.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
        binding.rvMalayalamList.setHasFixedSize(true)

        viewModel.getMalayalamList().observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
    }
}
