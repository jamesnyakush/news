package com.jamesnyakush.news

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.concurrent.TimeUnit

/**
 * Represents past time in text.
 * For e.g. 1 Minutes ago, 1 hour 0 minutes ago.
 */
@SuppressLint("SimpleDateFormat")
fun getPeriod(past: Date): String {

    val now = Date()
    val seconds = TimeUnit.MILLISECONDS.toSeconds(now.time - past.time)
    val minutes = TimeUnit.MILLISECONDS.toMinutes(now.time - past.time)
    val hours = TimeUnit.MILLISECONDS.toHours(now.time - past.time)

    return when {
        seconds < 60 -> {
            "Few seconds ago"
        }
        minutes < 60 -> {
            "$minutes minutes ago"
        }
        hours < 24 -> {
            "$hours hour ${minutes % 60} min ago"
        }
        else -> {
            SimpleDateFormat("dd/MM/yy, hh:mm a").format(past).toString()
        }
    }
}

/**
 * Parses String to "yyyy-MM-dd'T'HH:mm:ss" date and time format.
 */
@SuppressLint("SimpleDateFormat")
fun String.toDateFormat(): Date {
    return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        .parse(this)
}

object Payload{
    const val apiKey ="75cdd7daba1e4339b7cbccfe40a620b6"
    const val country ="us"
}
