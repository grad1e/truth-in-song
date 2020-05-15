package dev.rtrilia.truthinsong.ui.responsive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import dev.rtrilia.truthinsong.databinding.FragmentResponsiveListBinding

/**
 * A simple [Fragment] subclass.
 */
class ResponsiveListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentResponsiveListBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        val viewModel = ViewModelProvider(this).get(ResponsiveListFragmentViewModel::class.java)
        val navController = NavHostFragment.findNavController(this)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration)

        val adapter = ResponsiveListAdapter(ResponsiveListItemListener {
            navController.navigate(ResponsiveListFragmentDirections.actionScripturalListFragmentToDetailFragment(it))
        })
        binding.rvScripturalList.adapter = adapter
        binding.rvScripturalList.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
        binding.rvScripturalList.setHasFixedSize(true)


        viewModel.responsiveListList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        return binding.root
    }

}
