package com.example.tiptime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.tiptime.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWelcomeBinding.inflate(inflater,container,false)

        binding.btnNews.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_newsFragment)
        }

        binding.btnTiptime.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_calculatorFragment)
        }
        return binding.root






    }


}