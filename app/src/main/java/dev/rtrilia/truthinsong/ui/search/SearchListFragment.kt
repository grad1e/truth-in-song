package dev.rtrilia.truthinsong.ui.search

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import dev.rtrilia.truthinsong.R
import dev.rtrilia.truthinsong.databinding.DialogSearchListBinding
import timber.log.Timber

@AndroidEntryPoint
class SearchListFragment : DialogFragment(R.layout.dialog_search_list) {

    private val binding by viewBinding(DialogSearchListBinding::bind)
    private val viewModel by viewModels<SearchListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        openKeyboard()
        setupRecyclerView()
        setupObservables()
    }

    private fun setupObservables() {
        viewModel.searchString.observe(viewLifecycleOwner) {
            Timber.d("Search string: $it")
        }
    }

    private fun openKeyboard() {
        binding.tilSearch.requestFocus()
        if (binding.tilSearch.requestFocus()) {
            (requireActivity().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager).toggleSoftInput(
                InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY
            )
        }
    }

    private fun setupRecyclerView() {
        val adapter = SearchListAdapter {
            it.id?.let { id ->
                findNavController().navigate(
                    SearchListFragmentDirections.actionSearchDialogToSongFragment(
                        id
                    )
                )
            }
        }
        binding.recyclerView.adapter = adapter
        viewModel.getSearchList().observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
    }


    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onDestroyView() {
        binding.recyclerView.adapter = null
        super.onDestroyView()
    }

}