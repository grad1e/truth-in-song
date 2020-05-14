package dev.rtrilia.truthinsong.ui.search

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import dev.rtrilia.truthinsong.databinding.FragmentSearchListBinding

class SearchListFragment() : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentSearchListBinding.inflate(inflater, container, false)
        val viewModel = ViewModelProvider(this).get(SearchListFragmentViewModel::class.java)
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

        binding.tilEtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.getSearchList(s.toString())
            }
        })

        val adapter = SearchListFragmentAdapter(SearchListItemClickListener {
            navController.navigate(SearchListFragmentDirections.actionSearchFragmentToDetailFragment(it))
        })

        binding.recyclerView.adapter = adapter

        viewModel.searchList.observe(viewLifecycleOwner, Observer {
            adapter.setListData(it)
        })

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }


}