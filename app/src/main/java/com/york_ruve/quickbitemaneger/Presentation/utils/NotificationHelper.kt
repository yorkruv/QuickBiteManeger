package com.york_ruve.quickbitemaneger.Presentation.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.york_ruve.quickbitemaneger.Domain.Model.Ingredients
import com.york_ruve.quickbitemaneger.Presentation.Views.MainActivity
import com.york_ruve.quickbitemaneger.R

object NotificationHelper {
    const val CHANNEL_ID = "critical_ingredients_channel"
    const val NOTIFICATION_ID = 1
    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val Name = "Ingredientes criticos"
            val Description = "Notificaciones de ingredientes en estado crítico"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, Name, importance).apply {
                description = Description
            }
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun showCriticalIngredientNotification(
        context: Context,
        criticalIngredient: List<Ingredients>
    ) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val flags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) PendingIntent.FLAG_IMMUTABLE else 0
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, flags)
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_alert)
            .setContentTitle(context.getString(R.string.tittle_notification_critical))
            .setContentIntent(pendingIntent)
            .setContentText(
                context.getString(
                    R.string.stock_critical_message,
                    criticalIngredient.size
                )
            )
            .setStyle(
                NotificationCompat.BigTextStyle().bigText(
                    "${context.getString(R.string.ingredients)}: ${criticalIngredient.joinToString { it.name }}"
                )
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)
        if (NotificationManagerCompat.from(context).areNotificationsEnabled()) {
            with(NotificationManagerCompat.from(context)) {
                notify(NOTIFICATION_ID, builder.build())
            }
        }
    }
}