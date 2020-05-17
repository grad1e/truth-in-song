package dev.rtrilia.truthinsong.ui.malayalam

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import dev.rtrilia.truthinsong.R
import dev.rtrilia.truthinsong.SongApplication
import dev.rtrilia.truthinsong.databinding.FragmentMalayalamListBinding

/**
 * A simple [Fragment] subclass.
 */
class MalayalamListFragment : Fragment(R.layout.fragment_malayalam_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMalayalamListBinding.bind(view)
        val repository = (activity?.application as SongApplication).getRepository()
        val viewModel: MalayalamListViewModel by viewModels({ this }, { MalayalamListViewModelFactory(repository) })

        val navController = NavHostFragment.findNavController(this)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration)

        val adapter = MalayalamListAdapter(MalayalamListItemClickListener {
            navController.navigate(MalayalamListFragmentDirections.actionMalayalamListFragmentToDetailFragment(it))
        })
        binding.rvMalayalamList.adapter = adapter
        binding.rvMalayalamList.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
        binding.rvMalayalamList.setHasFixedSize(true)

        viewModel.getMalayalamList().observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }
}
