package dev.rtrilia.truthinsong.ui.search

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import dev.rtrilia.truthinsong.SongApplication
import dev.rtrilia.truthinsong.databinding.FragmentSearchListBinding

class SearchListFragment() : DialogFragment() {

    private lateinit var binding: FragmentSearchListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSearchListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = (activity?.application as SongApplication).getRepository()
        val viewModel: SearchListViewModel by viewModels({ this }, { SearchListViewModelFactory(repository) })

        val navController = NavHostFragment.findNavController(this)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.tilSearch.requestFocus()
        if (binding.tilSearch.requestFocus()) {
            (requireActivity().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager).toggleSoftInput(
                InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY
            )
        }

        val adapter = SearchListAdapter(SearchListItemClickListener {
            navController.navigate(SearchListFragmentDirections.actionSearchFragmentToDetailFragment(it))
        })

        binding.recyclerView.adapter = adapter

        viewModel.getSearchList().observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.setListData(it)
            }
        })


    }


    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }


}