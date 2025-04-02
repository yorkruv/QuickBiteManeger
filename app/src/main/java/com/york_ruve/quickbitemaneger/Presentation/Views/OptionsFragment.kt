package com.york_ruve.quickbitemaneger.Presentation.Views

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatDelegate
import com.york_ruve.quickbitemaneger.R
import com.york_ruve.quickbitemaneger.databinding.FragmentOptionsBinding
import java.util.Locale

class OptionsFragment : Fragment() {
    private lateinit var binding: FragmentOptionsBinding
    private val language = arrayOf("en", "es")
    private var isInitialSetup = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOptionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setListener()
        setUpSpinner()

    }

    private fun setUpSpinner() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.list_lenguage,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item
        ).also { adapter ->
            adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
            binding.spLanguage.adapter = adapter
        }
        val SharedPreferences =
            requireActivity().getSharedPreferences("settings", Context.MODE_PRIVATE)
        val savelanguage = SharedPreferences.getString("language", "en")
        val selectIndex = language.indexOf(savelanguage)
        isInitialSetup = true
        if (selectIndex != -1) binding.spLanguage.setSelection(selectIndex)

    }

    private fun init() {
        val SharedPreferences =
            requireActivity().getSharedPreferences("settings", Context.MODE_PRIVATE)
        val nightMode = SharedPreferences.getBoolean("night_mode", false)
        binding.scDarkTheme.isChecked = nightMode
    }

    private fun setListener() {
        val SharedPreferences =
            requireActivity().getSharedPreferences("settings", Context.MODE_PRIVATE)
        binding.scDarkTheme.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            val editor = SharedPreferences.edit()
            editor.putBoolean("night_mode", isChecked)
            editor.apply()
            requireActivity().recreate()
        }
        binding.spLanguage.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (isInitialSetup) {
                    isInitialSetup = false
                    return
                }
                val selectedlenguage = language[position]
                setLocate(selectedlenguage)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

    private fun setLocate(selectedlenguage: String) {

        val locate = Locale(selectedlenguage)
        Locale.setDefault(locate)
        val config = resources.configuration
        config.setLocale(locate)

        val context = requireContext().createConfigurationContext(config)
        resources.updateConfiguration(config, context.resources.displayMetrics)

        val sharedPreferences = requireActivity().getSharedPreferences("settings", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("language", selectedlenguage)
        editor.apply()

        requireActivity().recreate()
    }
}