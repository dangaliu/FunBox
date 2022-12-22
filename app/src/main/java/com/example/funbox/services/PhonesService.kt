package com.example.funbox.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.funbox.R
import com.example.funbox.model.entitiy.Phone
import com.example.funbox.model.repository.PhoneRepository
import com.example.funbox.model.storage.CsvPhoneStorage
import com.example.funbox.view.activity.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

const val phonesArrayKey = "PhonesArrayKey"

class PhonesService : Service() {
    override fun onBind(intent: Intent?): IBinder? = null

    private val channelId = "Phones Services ID"
    private val notificationManager by lazy { getSystemService(NotificationManager::class.java) }

    companion object {
        fun startService(context: Context, phones: ArrayList<Phone>) {
            val startIntent = Intent(context, PhonesService::class.java).also {
                it.putExtra(phonesArrayKey, phones)
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(startIntent)
            } else {
                context.startService(startIntent)
            }
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        }

        val myIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 111, myIntent, 0)


        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Saving Phones")
            .setContentText("saving phones...")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentIntent(pendingIntent)
            .build()
        startForeground(1, notification)

        val phones: ArrayList<Phone> =
            intent?.getParcelableArrayListExtra(phonesArrayKey) ?: arrayListOf()
        savePhonesToFile(PhoneRepository(CsvPhoneStorage()), phones)
        return START_STICKY
    }

    private fun savePhonesToFile(phoneRepository: PhoneRepository, phones: List<Phone>) = Thread {
        Log.d("service", "start")
        runBlocking(Dispatchers.IO) {
            phoneRepository.saveAll(phones)
            stopSelf()
        }
        Log.d("service", "end")
    }.start()


    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val notificationChannel = NotificationChannel(
            channelId,
            "Phone Notification channel",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(notificationChannel)
    }
}