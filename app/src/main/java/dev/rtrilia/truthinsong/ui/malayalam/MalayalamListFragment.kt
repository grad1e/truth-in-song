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
import dev.rtrilia.truthinsong.ui.home.HomeFragment

/**
 * A simple [Fragment] subclass.
 */
class MalayalamListFragment : Fragment() {

    companion object {
        fun newInstance() = MalayalamListFragment()
    }

    private lateinit var binding: FragmentMalayalamListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMalayalamListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = (activity?.application as SongApplication).getRepository()
        val viewModel: MalayalamListViewModel by viewModels({ this }, { MalayalamListViewModelFactory(repository) })

        val navController = findNavController()

        val adapter = MalayalamListAdapter(MalayalamListItemClickListener {
            navController.navigate(MalayalamListFragmentDirections.actionGlobalDetailFragment(it))
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
