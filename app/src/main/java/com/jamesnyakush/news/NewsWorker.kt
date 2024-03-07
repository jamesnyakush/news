package com.jamesnyakush.news

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.jamesnyakush.news.data.repository.NewsRepository
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


        result = Result.Success()

        Timber.d("News Loading.......")



        return result

    }

}