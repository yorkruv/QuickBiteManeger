package com.york_ruve.quickbitemaneger.Presentation.utils

import com.github.mikephil.charting.formatter.ValueFormatter

class DateAxisFormatter(private val salesDates: List<String>) : ValueFormatter() {

    override fun getFormattedValue(value: Float): String {
        val index = value.toInt()
        return if(index in salesDates.indices) salesDates[index] else ""
    }

}
