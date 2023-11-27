package com.example.tiptime

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.example.tiptime.databinding.FragmentWelcomeBinding
import java.util.Locale

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


        // Spinner setup fdr language selection
        val languages = arrayOf("English", "Swahili")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, languages)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                // Handle language selection
                when (position) {
                    0 -> setLocale("en") // English
                    1 -> setLocale("sw") // Swahili

                }


                // Update UI elements with translated strings
                updateUITranslations()
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // Do nothing
            }
        }
        return binding.root






    }


    private fun setLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration(resources.configuration)
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }


    private fun updateUITranslations() {
        // Update UI elements with translated strings
        binding.textSelectLangeuage.text = getString(R.string.select_language)
        binding.btnTiptime.text = getString(R.string.app_name)
        binding.btnNews.text = getString(R.string.news)
        // Add more UI elements as needed
    }


}