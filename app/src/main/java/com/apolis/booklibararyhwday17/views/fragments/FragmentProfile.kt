package com.apolis.booklibararyhwday17.views.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.apolis.booklibararyhwday17.R
import com.apolis.booklibararyhwday17.databinding.FragmentProfileBinding
import com.apolis.booklibararyhwday17.utils.Utils
import com.apolis.booklibararyhwday17.views.activities.Splash

class FragmentProfile : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var utils: Utils

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        utils = Utils(requireContext())

        binding.btnLogout.setOnClickListener {
            utils.clearPreferences()

            val intent = Intent(requireContext(), Splash::class.java)
            startActivity(intent)

            requireActivity().finish()
        }
    }


}