package com.utility

import java.util.Calendar

fun greet(hour: Int = Calendar.getInstance()
    .get(Calendar.HOUR_OF_DAY)): String {
    return when (hour) {
        in 4..11 -> "Good Morning"
        in 12..16 -> "Good Afternoon"
        in 17..20 -> "Good Evening"
        else -> "Good Night"
    }
}
