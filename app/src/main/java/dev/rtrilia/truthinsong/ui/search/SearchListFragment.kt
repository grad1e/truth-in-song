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
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.rtrilia.truthinsong.databinding.FragmentSearchListBinding
import timber.log.Timber

@AndroidEntryPoint
class SearchListFragment : DialogFragment() {

    private lateinit var binding: FragmentSearchListBinding
    private val viewModel by viewModels<SearchListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        openKeyboard()
        setupRecyclerView()
        setupObservables()
    }

    private fun setupObservables() {
        viewModel.searchString.observe(viewLifecycleOwner){
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
                    SearchListFragmentDirections.actionSearchFragmentToDetailFragment(
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