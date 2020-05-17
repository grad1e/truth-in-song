package dev.rtrilia.truthinsong.ui.english

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
import dev.rtrilia.truthinsong.databinding.FragmentEnglishListBinding

/**
 * A simple [Fragment] subclass.
 */
class EnglishListFragment : Fragment(R.layout.fragment_english_list) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentEnglishListBinding.bind(view)

        binding.lifecycleOwner = viewLifecycleOwner

        val repository = (activity?.application as SongApplication).getRepository()
        val viewModel: EnglishListViewModel by viewModels({ this }, { EnglishListViewModelFactory(repository) })

        val navController = NavHostFragment.findNavController(this)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration)

        val adapter = EnglishListAdapter(EnglishListItemListener {
            navController.navigate(EnglishListFragmentDirections.actionEnglishListFragmentToDetailFragment(it))
        })
        binding.rvEnglishList.adapter = adapter
        binding.rvEnglishList.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
        binding.rvEnglishList.setHasFixedSize(true)

        viewModel.getEnglishList().observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })


    }


}
