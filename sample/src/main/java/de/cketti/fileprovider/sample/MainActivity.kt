package de.cketti.fileprovider.sample


import android.app.NotificationManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v4.content.FileProvider
import android.support.v7.app.AppCompatActivity
import android.util.Log
import de.cketti.fileprovider.PublicFileProvider
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class MainActivity : AppCompatActivity() {
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    private lateinit var soundFileProvider: SoundFileProvider


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        soundFileProvider = SoundFileProvider(applicationContext)

        findViewById(R.id.button_create_notification_publicfileprovider).setOnClickListener {
            createNotificationWithPublicFileProvider()
        }
        findViewById(R.id.button_create_notification_fileprovider).setOnClickListener {
            createNotificationWithFileProvider()
        }
        findViewById(R.id.button_create_notification_file_uri).setOnClickListener {
            createNotificationWithFileUri()
        }
    }

    private fun createNotificationWithPublicFileProvider() {
        val notificationData = NotificationData(
                id = 1,
                buildSoundUri = {
                    PublicFileProvider.getUriForFile(
                            applicationContext,
                            "de.cketti.fileprovider.test",
                            soundFileProvider.soundFile)
                },
                title = "PublicFileProvider Test",
                contentText = "Notification sound should work fine on Android 7 🎉"
        )
        createNotificationInBackground(notificationData)
    }

    private fun createNotificationWithFileProvider() {
        val notificationData = NotificationData(
                id = 2,
                buildSoundUri = {
                    FileProvider.getUriForFile(
                            applicationContext,
                            "de.cketti.fileprovider.secure",
                            soundFileProvider.soundFile)
                },
                title = "FileProvider Test",
                contentText = "Notification sound can't be played on Android 7 😞"
        )
        createNotificationInBackground(notificationData)
    }

    private fun createNotificationWithFileUri() {
        val notificationData = NotificationData(
                id = 3,
                buildSoundUri = { Uri.fromFile(soundFileProvider.soundFile) },
                title = "file:// URI Test",
                contentText = "This will throw FileUriExposedException on Android 7 💥"
        )
        createNotificationInBackground(notificationData)
    }

    private fun createNotificationInBackground(notificationData: NotificationData) {
        executorService.submit { createNotification(notificationData) }
    }

    private fun createNotification(notificationData: NotificationData) {
        val notification = NotificationCompat.Builder(applicationContext)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(notificationData.title)
                .setContentText(notificationData.contentText)
                .setSound(notificationData.buildSoundUri())
                .build()

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        try {
            notificationManager.notify(notificationData.id, notification)
        } catch (e: Exception) {
            Log.e("BOOM", "Exception while calling NotificationManager.notify()", e)
        }
    }
}
