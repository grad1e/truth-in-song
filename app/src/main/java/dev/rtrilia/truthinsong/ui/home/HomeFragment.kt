package dev.rtrilia.truthinsong.ui.home

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import dev.rtrilia.truthinsong.R
import dev.rtrilia.truthinsong.data.database.SongBookDao
import dev.rtrilia.truthinsong.databinding.FragmentHomeBinding
import dev.rtrilia.truthinsong.ui.MainActivity
import dev.rtrilia.truthinsong.ui.english.EnglishListFragment
import dev.rtrilia.truthinsong.ui.malayalam.MalayalamListFragment
import dev.rtrilia.truthinsong.ui.responsive.ResponsiveListFragment
import dev.rtrilia.truthinsong.util.UiMode
import javax.inject.Inject

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

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        (activity as MainActivity).supportActionBar?.title = "Truth in Song"
        setupViewPager()
    }

    private fun setupViewPager() {
        binding.homeViewpager.adapter = HomeFragmentViewPagerAdapter(childFragmentManager, lifecycle)
        TabLayoutMediator(binding.homeTabLayout, binding.homeViewpager) { tab, position ->
            tab.text = viewPagerFragmentLabel()[position]
        }.attach()
        //binding.homeViewpager.offscreenPageLimit = viewPagerFragmentList().size
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_home, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.theme -> openThemePickerDialog()
            R.id.about -> findNavController().navigate(HomeFragmentDirections.actionGlobalAboutFragment())
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openThemePickerDialog() {
        val styles = arrayOf("Light", "Dark", "System default")
        val checkedItem = viewModel.getUiMode()
        MaterialAlertDialogBuilder(requireContext()).apply {
            setTitle("Choose Theme")
            setSingleChoiceItems(styles, checkedItem) { dialog, uiMode ->
                when (uiMode) {
                    UiMode.LIGHT_MODE -> {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                        viewModel.setUiMode(UiMode.LIGHT_MODE)
                        dialog.cancel()
                    }
                    UiMode.DARK_MODE -> {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                        viewModel.setUiMode(UiMode.DARK_MODE)
                        dialog.cancel()
                    }
                    UiMode.SYSTEM_DEFAULT -> {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                        viewModel.setUiMode(UiMode.SYSTEM_DEFAULT)
                        dialog.cancel()
                    }
                }
            }
            create()
            show()
        }
    }


}