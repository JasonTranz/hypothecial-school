package com.jason.littlelives.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun String.toTimeStamp(
    pattern: String = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
): Long {
    return try {
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        sdf.parse(this)?.time ?: 0L
    } catch (e: Exception) {
        0L
    }
}

fun Long.toDate(
    pattern: String = "EEE, MMM dd, yyyy"
): String {
    val date = Date(this)
    val df = SimpleDateFormat(pattern, Locale.getDefault())
    return df.format(date)
}