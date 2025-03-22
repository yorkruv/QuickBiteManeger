package com.york_ruve.quickbitemaneger.Presentation.Views

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.york_ruve.quickbitemaneger.R
import com.york_ruve.quickbitemaneger.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

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

                else -> false
            }
        }
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