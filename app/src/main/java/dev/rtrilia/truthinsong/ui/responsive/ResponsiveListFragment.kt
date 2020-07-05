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

    companion object {
        fun newInstance() = ResponsiveListFragment()
    }

    private lateinit var binding: FragmentResponsiveListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentResponsiveListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = (activity?.application as SongApplication).getRepository()
        val viewModel: ResponsiveListViewModel by viewModels({ this }, { ResponsiveListViewModelFactory(repository) })

        val navController = findNavController()

        val adapter = ResponsiveListAdapter(ResponsiveListItemListener {
            navController.navigate(ResponsiveListFragmentDirections.actionGlobalDetailFragment(it))
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
