package com.jamesnyakush.news

import androidx.room.Room
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.google.gson.GsonBuilder
import com.jamesnyakush.news.data.db.NewsDAO
import com.jamesnyakush.news.data.db.NewsDB
import com.jamesnyakush.news.data.network.ApiClient
import com.jamesnyakush.news.data.repository.NewsRepository
import com.jamesnyakush.news.data.repository.NewsRepositoryImpl
import com.jamesnyakush.news.ui.viewmodel.NewsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val viewModelModule = module {
    viewModel { NewsViewModel(get()) }
}

val dbModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            NewsDB::class.java,
            "news.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<NewsDB>().newsDao() }
}

val repositoryModule: Module = module {
    single<NewsRepository> { NewsRepositoryImpl(get(),get()) }
}

val networkingModules: Module = module {
    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = when (BuildConfig.BUILD_TYPE) {
            "release" -> HttpLoggingInterceptor.Level.NONE
            else -> HttpLoggingInterceptor.Level.BODY
        }

        // Create the Collector
        val chuckerCollector = ChuckerCollector(
            context = androidContext(),
            showNotification = true,
            retentionPeriod = RetentionManager.Period.ONE_HOUR
        )

        // Create the Interceptor
        val chuckerInterceptor = ChuckerInterceptor.Builder(androidContext())
            .collector(chuckerCollector)
            .maxContentLength(250_000L)
            .redactHeaders("Auth-Token", "Bearer")
            .alwaysReadResponseBody(true)
            .build()

        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(chuckerInterceptor)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    single {
        val gson = GsonBuilder()
            .serializeNulls()
            .create()

        Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(get())
            .build()
    }


}

val apiModules: Module = module {
    single<ApiClient> { get<Retrofit>().create(ApiClient::class.java) }
}

val dataModule: List<Module> = listOf(
    repositoryModule,
    networkingModules,
    apiModules
)