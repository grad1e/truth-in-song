package dev.rtrilia.truthinsong.ui.responsive

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
import dev.rtrilia.truthinsong.databinding.FragmentResponsiveListBinding

/**
 * A simple [Fragment] subclass.
 */
class ResponsiveListFragment : Fragment(R.layout.fragment_responsive_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentResponsiveListBinding.bind(view)
        val repository = (activity?.application as SongApplication).getRepository()
        val viewModel: ResponsiveListViewModel by viewModels({ this }, { ResponsiveListViewModelFactory(repository) })

        val navController = NavHostFragment.findNavController(this)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration)

        val adapter = ResponsiveListAdapter(ResponsiveListItemListener {
            navController.navigate(ResponsiveListFragmentDirections.actionScripturalListFragmentToDetailFragment(it))
        })
        binding.rvScripturalList.adapter = adapter
        binding.rvScripturalList.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
        binding.rvScripturalList.setHasFixedSize(true)


        viewModel.getResponsiveList().observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }


}
