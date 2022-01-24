package com.example.appkode.presentation

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.appkode.R
import com.example.appkode.databinding.FragmentHomeViewPagerBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeViewPagerFragment : Fragment() {

    private var _binding: FragmentHomeViewPagerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeViewPagerBinding.inflate(inflater, container, false)

        binding.viewPager.adapter = PagerAdapter(this)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        setHasOptionsMenu(true)
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                requireActivity().invalidateOptionsMenu()
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.e("Selected_Page", position.toString())
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar_menu, menu)
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            PAGE_INDEX_0 -> getString(R.string.all)
            PAGE_INDEX_1 -> getString(R.string.android)
            PAGE_INDEX_2 -> getString(R.string.ios)
            PAGE_INDEX_3 -> getString(R.string.design)
            PAGE_INDEX_4 -> getString(R.string.management)
            PAGE_INDEX_5 -> getString(R.string.qa)
            PAGE_INDEX_6 -> getString(R.string.back_office)
            PAGE_INDEX_7 -> getString(R.string.frontend)
            PAGE_INDEX_8 -> getString(R.string.hr)
            PAGE_INDEX_9 -> getString(R.string.pr)
            PAGE_INDEX_10 -> getString(R.string.backend)
            PAGE_INDEX_11 -> getString(R.string.support)
            PAGE_INDEX_12 -> getString(R.string.analytics)
            else -> null
        }
    }
}