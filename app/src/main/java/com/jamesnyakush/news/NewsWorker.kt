package com.jamesnyakush.news

import android.content.Context
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.jamesnyakush.news.data.Response
import com.jamesnyakush.news.data.repository.NewsRepository
import com.jamesnyakush.news.ui.component.NewsCard
import kotlinx.coroutines.flow.firstOrNull
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import timber.log.Timber

class NewsWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params), KoinComponent {

    private val repository: NewsRepository by inject()
    override suspend fun doWork(): Result {
        var result: Result = Result.Failure()



        NotificationHelper(applicationContext).createNotification(
            "article.title!!","Doneth"
        )


        return Result.Success()

    }

}