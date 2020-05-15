package dev.rtrilia.truthinsong.ui.english

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import dev.rtrilia.truthinsong.databinding.FragmentEnglishListBinding

/**
 * A simple [Fragment] subclass.
 */
class EnglishListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentEnglishListBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        val viewModel = ViewModelProvider(this).get(EnglishListFragmentViewModel::class.java)
        val navController = NavHostFragment.findNavController(this)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration)

        val adapter = EnglishListAdapter(EnglishListItemListener {
            navController.navigate(EnglishListFragmentDirections.actionEnglishListFragmentToDetailFragment(it))
        })
        binding.rvEnglishList.adapter = adapter
        binding.rvEnglishList.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
        binding.rvEnglishList.setHasFixedSize(true)



        viewModel.englishListList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        return binding.root
    }


}
