package com.nikhil.startertemplate.screens.landing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.nikhil.startertemplate.R
import com.nikhil.startertemplate.base.BaseFragment
import com.nikhil.startertemplate.databinding.FragmentLandingBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LandingFragment : BaseFragment<LandingEvent, LandingViewModel>() {

    private lateinit var binding: FragmentLandingBinding
    override val viewModel: LandingViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_landing, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

}