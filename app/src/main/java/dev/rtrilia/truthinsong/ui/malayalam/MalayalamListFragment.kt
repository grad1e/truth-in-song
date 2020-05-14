package dev.rtrilia.truthinsong.ui.malayalam

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
import dev.rtrilia.truthinsong.databinding.FragmentMalayalamListBinding

/**
 * A simple [Fragment] subclass.
 */
class MalayalamListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentMalayalamListBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        val viewModel = ViewModelProvider(this).get(MalayalamListFragmentViewModel::class.java)
        val navController = NavHostFragment.findNavController(this)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration)

        val adapter = MalayalamListAdapter(MalayalamListItemClickListener {
            navController.navigate(MalayalamListFragmentDirections.actionMalayalamListFragmentToDetailFragment(it))
        })
        binding.rvMalayalamList.adapter = adapter
        binding.rvMalayalamList.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))

        viewModel.malayalamList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })


        return binding.root

    }

}
