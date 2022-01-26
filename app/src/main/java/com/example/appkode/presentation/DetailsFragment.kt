package com.example.appkode.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.appkode.R
import com.example.appkode.databinding.FragmentDetailsBinding
import com.example.appkode.domain.User


class DetailsFragment : Fragment(R.layout.fragment_details) {

    val args  by navArgs<DetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val bind = FragmentDetailsBinding.bind(view)
        bind.backBottom.setOnClickListener { findNavController().popBackStack() }
        bind.nameDetails.text = args.user.lastName
        bind.tagDetails.text = args.user.userTag
        bind.departmentDetails.text = args.user.department
    }

}

