package dev.rtrilia.truthinsong.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import dev.rtrilia.truthinsong.databinding.HomeFragmentBinding
import dev.rtrilia.truthinsong.ui.english.EnglishListFragment
import dev.rtrilia.truthinsong.ui.malayalam.MalayalamListFragment
import dev.rtrilia.truthinsong.ui.responsive.ResponsiveListFragment

@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {

        private fun viewPagerFragmentList() = listOf(
            MalayalamListFragment(),
            EnglishListFragment(),
            ResponsiveListFragment()
        )

        private fun viewPagerFragmentLabel() = listOf(
            "Malayalam",
            "English",
            "Responsive"
        )

    }

    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
        (activity as HomeActivity).setToolbarTitle("Truth in Song")
    }

    private fun setupViewPager() {
        binding.homeViewpager.adapter = HomeFragmentViewPagerAdapter(childFragmentManager, lifecycle)
        TabLayoutMediator(binding.homeTabLayout, binding.homeViewpager) { tab, position ->
            tab.text = viewPagerFragmentLabel()[position]
        }.attach()
    }

    inner class HomeFragmentViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(fragmentManager, lifecycle) {
        override fun getItemCount(): Int = viewPagerFragmentList().size
        override fun createFragment(position: Int): Fragment = viewPagerFragmentList()[position]
    }

    override fun onDestroyView() {
        binding.homeViewpager.adapter = null
        super.onDestroyView()
    }

}