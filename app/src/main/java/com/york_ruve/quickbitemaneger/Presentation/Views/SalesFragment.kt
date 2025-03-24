package com.york_ruve.quickbitemaneger.Presentation.Views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.york_ruve.quickbitemaneger.R
import com.york_ruve.quickbitemaneger.databinding.FragmentSalesBinding


class SalesFragment : Fragment() {

    private lateinit var binding:FragmentSalesBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSalesBinding.inflate(inflater,container,false)
        return binding.root
    }


}