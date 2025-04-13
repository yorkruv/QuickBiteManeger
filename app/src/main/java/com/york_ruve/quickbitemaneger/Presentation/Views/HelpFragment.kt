package com.york_ruve.quickbitemaneger.Presentation.Views

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.york_ruve.quickbitemaneger.R
import com.york_ruve.quickbitemaneger.databinding.FragmentHelpBinding


class HelpFragment : Fragment() {

    private lateinit var binding: FragmentHelpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHelpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setListeners()
    }

    private fun setListeners() {
        binding.lyHelp1.setOnClickListener {
            toggleHelp(binding.tvHelp1, binding.ivHelp1)
        }
        binding.lyHelp2.setOnClickListener {
            toggleHelp(binding.tvHelp2, binding.ivHelp2)
        }
        binding.lyHelp3.setOnClickListener {
            toggleHelp(binding.tvHelp3, binding.ivHelp3)
        }
        binding.lyHelp4.setOnClickListener {
            toggleHelp(binding.tvHelp4, binding.ivHelp4)
        }
        binding.lyHelp5.setOnClickListener {
            toggleHelp(binding.tvHelp5, binding.ivHelp5)
        }
        binding.lyHelp6.setOnClickListener {
            toggleHelp(binding.tvHelp6, binding.ivHelp6)
        }
        binding.lyHelp7.setOnClickListener {
            toggleHelp(binding.tvHelp7, binding.ivHelp7)
        }
        binding.lyHelp8.setOnClickListener {
            toggleHelp(binding.tvHelp8, binding.ivHelp8)
        }
        binding.lyHelp9.setOnClickListener {
            toggleHelp(binding.tvHelp9, binding.ivHelp9)
        }
        binding.lyHelp10.setOnClickListener {
            toggleHelp(binding.tvHelp10, binding.ivHelp10)
        }
        binding.btnContact.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND).apply {
                data = Uri.parse("mailto:${requireContext().getString(R.string.support_email)}")
                putExtra(Intent.EXTRA_SUBJECT, requireContext().getString(R.string.email_subject))
                putExtra(Intent.EXTRA_TEXT, requireContext().getString(R.string.email_body))
            }
            startActivity(Intent.createChooser(intent, requireContext().getString(R.string.send_email)))
        }
    }

    private fun toggleHelp(textView: TextView, imageView: ImageView){
        if (textView.visibility == View.GONE) {
            textView.visibility = View.VISIBLE
            imageView.setImageResource(R.drawable.ic_desp_open)
        }else{
            textView.visibility = View.GONE
            imageView.setImageResource(R.drawable.ic_desp_close)
        }
    }
}