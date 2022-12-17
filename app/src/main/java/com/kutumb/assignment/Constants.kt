package com.kutumb.assignment

class Constants {
    companion object {
        const val KEY_TIME = "time"
        const val CHANNEL_TIMER = "Timer"
        const val ID_NOTIFICATION = 100

        fun parse(time: Long): String {
            val seconds = time / 1000
            val second: Int = (seconds % 60).toInt()
            val minutes: Int = (seconds / 60).toInt()
            return "${minutes}:${second}"
        }
    }
}