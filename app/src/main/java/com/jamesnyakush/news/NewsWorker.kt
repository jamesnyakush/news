package com.jamesnyakush.news

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.jamesnyakush.news.data.Response
import com.jamesnyakush.news.data.repository.NewsRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collectLatest
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import timber.log.Timber


class NewsWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params), KoinComponent {

    private val repository: NewsRepository by inject()

    override suspend fun doWork(): Result = coroutineScope {
        var result = Result.failure()

        val article = repository.getTopHeadlines("us", "75cdd7daba1e4339b7cbccfe40a620b6")

        article.collectLatest { response ->
            when (response) {
                is Response.Success -> {

                    val title = response.data!!.articles.firstOrNull()?.title
                    val desc = response.data.articles.firstOrNull()?.description
                   // val img = response.data.articles.firstOrNull()?.urlToImage

                    for (res in response.data.articles){
                        showNotification(
                            title = res.title!!,
                            message = res.description!!,
                            //img = img!!
                        )
                    }
//                    showNotification(
//                        title = title!!,
//                        message = desc!!,
//                        //img = img!!
//                    )
                    result = Result.success()
                }

                is Response.Failure -> {

                    Timber.d(response.e?.message)
                    result = Result.failure()
                }

                else -> {}
            }
        }

        result
    }


    @SuppressLint("StringFormatInvalid")
    private fun showNotification(title: String, message: String) {
        val channelId = "reminder_channel_id"
        val channelName = "d"

       // val bitmap = (img as BitmapDrawable).bitmap


        val intent = Intent(applicationContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }


        val pendingFlags: Int =
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE

        val pendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, pendingFlags)


        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notificationBuilder = NotificationCompat.Builder(applicationContext, channelId)
            .setColor(ContextCompat.getColor(applicationContext, R.color.black))
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle(
                title
            )

//            .setLargeIcon(bitmap)
//            .setStyle(
//                NotificationCompat.BigPictureStyle()
//                    .bigPicture(bitmap)
//                //.bigLargeIcon(null)
//            )

            .setContentText(message)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)

        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0, notificationBuilder.build())
    }
}