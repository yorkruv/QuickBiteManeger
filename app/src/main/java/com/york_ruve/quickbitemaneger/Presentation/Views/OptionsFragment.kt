package com.york_ruve.quickbitemaneger.Presentation.Views

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import com.york_ruve.quickbitemaneger.R
import com.york_ruve.quickbitemaneger.databinding.FragmentOptionsBinding

class OptionsFragment : Fragment() {
    private lateinit var binding: FragmentOptionsBinding

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

    }

    private fun init() {
        val SharedPreferences = requireActivity().getSharedPreferences("settings", Context.MODE_PRIVATE)
        val nightMode = SharedPreferences.getBoolean("night_mode", false)
        binding.scDarkTheme.isChecked = nightMode
    }

    private fun setListener() {
        val SharedPreferences = requireActivity().getSharedPreferences("settings", Context.MODE_PRIVATE)
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
    }
}