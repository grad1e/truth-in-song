package dev.rtrilia.truthinsong.ui.english

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import dev.rtrilia.truthinsong.SongApplication
import dev.rtrilia.truthinsong.databinding.FragmentEnglishListBinding

/**
 * A simple [Fragment] subclass.
 */
class EnglishListFragment : Fragment() {

    private lateinit var binding: FragmentEnglishListBinding
    private lateinit var factory: EnglishListViewModelFactory
    private val viewModel by viewModels<EnglishListViewModel>({ this }, { factory })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentEnglishListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        val repository = (activity?.application as SongApplication).getRepository()
        factory = EnglishListViewModelFactory(repository)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val adapter = EnglishListAdapter {
            it.id?.let { id ->
                findNavController().navigate(EnglishListFragmentDirections.actionGlobalDetailFragment(id))
            }
        }
        binding.rvEnglishList.adapter = adapter
        adapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        binding.rvEnglishList.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
        binding.rvEnglishList.setHasFixedSize(true)

        viewModel.getEnglishList().observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
    }


}
