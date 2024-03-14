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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import timber.log.Timber


class NewsWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params), KoinComponent {

    private val repository: NewsRepository by inject()
    override suspend fun doWork(): Result = coroutineScope {
        Timber.d("start")
        var result = Result.failure()

        val article = repository.getTopHeadlines("us", "75cdd7daba1e4339b7cbccfe40a620b6")

        article.collectLatest {response ->
            when (response) {
                is Response.Success -> {

//                    NotificationHelper(applicationContext).createNotification(
//                        "it.data!!.articles[10].description.toString()", ""
//                    )
                    Timber.d("Response: ${response.data!!.articles[10].title} \n ${response.data!!.articles[10].description}")
                    result = Result.success()
                }

                else -> {}
            }


        }


        result
    }

}