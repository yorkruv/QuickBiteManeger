package com.york_ruve.quickbitemaneger.Presentation.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.room.Room
import com.york_ruve.quickbitemaneger.Data.DataBase.quickBiteDatabase
import com.york_ruve.quickbitemaneger.Domain.Model.Ingredients
import com.york_ruve.quickbitemaneger.Presentation.ViewModels.ingredientViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DailyNotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, p1: Intent?) {
        CoroutineScope(Dispatchers.IO).launch {
            val db = Room.databaseBuilder(
                context,
                quickBiteDatabase::class.java,
                "database_quickBite"
            ).build()
            val ingredientesCriticos = db.ingredientsDao().getCriticalIngredients().map {
                Ingredients(
                    it.ingredientId,
                    it.nombre,
                    it.stock,
                    it.unidad,
                    it.criticalQuantity
                )
            }
            NotificationHelper.showCriticalIngredientNotification(context, ingredientesCriticos)
        }
    }
}