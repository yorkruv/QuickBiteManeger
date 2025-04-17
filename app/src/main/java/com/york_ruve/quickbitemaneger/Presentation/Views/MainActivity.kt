package com.york_ruve.quickbitemaneger.Presentation.Views

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.york_ruve.quickbitemaneger.Presentation.utils.DailyNotificationReceiver
import com.york_ruve.quickbitemaneger.Presentation.utils.NotificationHelper
import com.york_ruve.quickbitemaneger.Presentation.utils.NotificationHelper.NOTIFICATION_ID
import com.york_ruve.quickbitemaneger.R
import com.york_ruve.quickbitemaneger.databinding.ActivityMainBinding
import com.york_ruve.quickbitemaneger.databinding.PopupMenuBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Calendar
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
        scheduleDailyNotification()
        lifecycleScope.launch {
            NotificationHelper.createNotificationChannel(this@MainActivity)
        }
    }

    private fun scheduleDailyNotification() {
        val intent = Intent(applicationContext, DailyNotificationReceiver::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(
                applicationContext,
                NOTIFICATION_ID,
                intent,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 9)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }
        if (calendar.timeInMillis < System.currentTimeMillis()) {
            calendar.add(Calendar.DAY_OF_YEAR, 1)
        }
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }

    private fun Permissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.POST_NOTIFICATIONS
                )
                != PackageManager.PERMISSION_GRANTED
            ) {

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

        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
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
            true
        )

        binding.tvOption.setOnClickListener {
            loadFragment(OptionsFragment())
            popupWindow.dismiss()
        }
        binding.tvHelp.setOnClickListener {
            loadFragment(HelpFragment())
            popupWindow.dismiss()
        }
        popupWindow.showAsDropDown(view, 0, 10)
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.MainFragment.id, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun updateToolbarTitleAndNavigation(title: String, itemId: Int) {
        binding.tvPantalla.text = title
        binding.bottomNavigationView.selectedItemId = itemId
    }
}