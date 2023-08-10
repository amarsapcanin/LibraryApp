package com.apolis.booklibararyhwday17.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.apolis.booklibararyhwday17.ViewpagerAdapter
import com.apolis.booklibararyhwday17.databinding.FragmentLibraryBinding
import com.apolis.booklibararyhwday17.views.fragments.categoryFragments.FragmentAction
import com.apolis.booklibararyhwday17.views.fragments.categoryFragments.FragmentAllBooks
import com.apolis.booklibararyhwday17.views.fragments.categoryFragments.FragmentDrama
import com.apolis.booklibararyhwday17.views.fragments.categoryFragments.FragmentRomance
import com.google.android.material.tabs.TabLayoutMediator

class FragmentLibrary : Fragment() {

    private lateinit var binding: FragmentLibraryBinding // Declare binding variable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLibraryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragments = listOf(FragmentAllBooks(), FragmentAction(), FragmentDrama(), FragmentRomance())
        val adapter = ViewpagerAdapter(fragments, requireActivity())

        binding.viewPager2.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "All Books"
                }
                1 -> {
                    tab.text = "Action Books"
                }
                2 -> {
                    tab.text = "Drama Books"
                }
                3 -> {
                    tab.text = "Romance Books"
                }
            }
        }.attach()
    }
}
