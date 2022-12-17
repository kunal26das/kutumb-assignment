package com.kutumb.assignment

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import com.kutumb.assignment.Constants.Companion.CHANNEL_TIMER

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private val mainActivity = registerForActivityResult(MainActivity.Contract()) {}
    private val timerActivity = registerForActivityResult(CountDownActivity.Contract()) {}
    private val notificationManager by lazy { NotificationManagerCompat.from(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (CountDownService.isRunning) {
            timerActivity.launch(null)
        } else {
            mainActivity.launch(null)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        }
        finish()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val channel = NotificationChannel(CHANNEL_TIMER, CHANNEL_TIMER, IMPORTANCE_HIGH)
        notificationManager.createNotificationChannel(channel)
    }

}