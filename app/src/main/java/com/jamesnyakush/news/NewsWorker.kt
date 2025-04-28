package com.jamesnyakush.news

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.jamesnyakush.news.data.Response
import com.jamesnyakush.news.data.model.Article
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

        val article = repository.getTopHeadlines(Payload.country, Payload.apiKey)

        article.collectLatest { response ->
            when (response) {
                is Response.Success -> {
                    for (res in response.data?.articles!!) {
                        showNotification(
                            article = res
                        )
                    }
                    result = Result.success()
                }

                is Response.Failure -> {
                    Timber.d(response.e)
                    Timber.d(response.e?.localizedMessage.toString())
                    result = Result.failure()
                }

                else -> {}
            }
        }

        result
    }


    private suspend fun showNotification(article: Article) {
        val channelId = "reminder_channel_id"
        val channelName = "d"


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
            .setContentTitle(article.title)
            .setStyle(
                NotificationCompat.BigPictureStyle().bigPicture(getBitmap(article.urlToImage))
            )
            .setContentText(article.description)
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


    //Convert url to Bitmap
    private suspend fun getBitmap(image: String?): Bitmap {
        val loading = ImageLoader(applicationContext)
        val request = ImageRequest.Builder(applicationContext)
            .data(image!!)
            .build()

        val result = (loading.execute(request = request) as SuccessResult).drawable

        return (result as BitmapDrawable).bitmap
    }
}