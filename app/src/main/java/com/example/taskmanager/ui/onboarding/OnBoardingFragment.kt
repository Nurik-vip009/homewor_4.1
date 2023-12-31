package com.example.taskmanager.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.R
import com.example.taskmanager.data.local.Pref
import com.example.taskmanager.databinding.FragmentOnBoardingBinding
import com.example.taskmanager.ui.onboarding.adapter.OnBoardingAdapter
import com.example.taskmanager.ui.task.model.OnBoarding

class OnBoardingFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardingBinding
    private val pref by lazy {
        Pref(requireContext())
    }
    private val adapter = OnBoardingAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = adapter
        binding.indicator.setViewPager(binding.viewPager)
        binding.btnGet.setOnClickListener{
            onClick()
        }
        binding.btnSkip.setOnClickListener{
            onClick()
        }
    }


    private fun onClick() {
        pref.userShowed()
        findNavController().navigateUp()
    }

}

