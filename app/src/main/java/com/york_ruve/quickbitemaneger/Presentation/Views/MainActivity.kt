package com.york_ruve.quickbitemaneger.Presentation.Views

import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.york_ruve.quickbitemaneger.R
import com.york_ruve.quickbitemaneger.databinding.ActivityMainBinding
import com.york_ruve.quickbitemaneger.databinding.PopupMenuBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        loadFragment(dashBoardFragment())
        setListeners()
        setNightMode()
        applyLanguage()
        Permissions()
    }

    private fun Permissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(
                    this, // o activity si estás en un Activity
                    arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                    1001 // Código de solicitud
                )
            }
        }
    }

    private fun applyLanguage() {
        val sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE)
        val lenguage = sharedPreferences.getString("language", "en")
        val config = resources.configuration
        val locale = Locale(lenguage)
        Locale.setDefault(locale)
        config.setLocale(locale)
        applicationContext.createConfigurationContext(config)
    }

    private fun setNightMode() {
        val sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE)
        val isNightMode = sharedPreferences.getBoolean("night_mode", false)

        if (isNightMode){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun setListeners() {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    loadFragment(dashBoardFragment())
                    binding.tvPantalla.text = getString(R.string.DashBoard)
                    true
                }
                R.id.nav_orders -> {
                    loadFragment(OrdersFragment())
                    binding.tvPantalla.text = getString(R.string.Order_management)
                    true
                }

                R.id.nav_menu -> {
                    loadFragment(MenuFragment())
                    binding.tvPantalla.text = getString(R.string.Menu_management)
                    true
                }

                R.id.nav_inventory -> {
                    loadFragment(InventoryFragment())
                    binding.tvPantalla.text = getString(R.string.Inventory_management)
                    true
                }

                R.id.nav_sales -> {
                    loadFragment(SalesFragment())
                    binding.tvPantalla.text = getString(R.string.Sales_management)
                    true
                }

                else -> false
            }
        }
        binding.ivPerfil.setOnClickListener {
            showPopup(it)
        }
    }

    private fun showPopup(view: View?) {
        val inflater = LayoutInflater.from(this)
        val binding = PopupMenuBinding.inflate(layoutInflater)
        val popupView = binding.root
        val popupWindow = PopupWindow(
            popupView,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            true)

        binding.tvOption.setOnClickListener {
            loadFragment(OptionsFragment())
            popupWindow.dismiss()
        }
        binding.tvHelp.setOnClickListener {
            loadFragment(HelpFragment())
            popupWindow.dismiss()
        }
        popupWindow.showAsDropDown(view,0,10)
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.MainFragment.id, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun updateToolbarTitleAndNavigation(title: String, itemId:Int){
        binding.tvPantalla.text = title
        binding.bottomNavigationView.selectedItemId = itemId
    }
}