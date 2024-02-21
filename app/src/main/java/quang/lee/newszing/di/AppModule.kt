package quang.lee.newszing.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import quang.lee.newszing.data.manger.LocalUserManagerImp
import quang.lee.newszing.data.remote.NewsApi
import quang.lee.newszing.data.remote.repository.NewsRepositoryImp
import quang.lee.newszing.domain.manager.LocalUserManager
import quang.lee.newszing.domain.repository.NewsRepository
import quang.lee.newszing.domain.usecases.app_entry.AppEntryUseCases
import quang.lee.newszing.domain.usecases.app_entry.ReadAppEntry
import quang.lee.newszing.domain.usecases.app_entry.SaveAppEntry
import quang.lee.newszing.domain.usecases.news.GetNews
import quang.lee.newszing.domain.usecases.news.NewsUseCases
import quang.lee.newszing.domain.usecases.news.SearchNews
import quang.lee.newszing.util.Constant.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImp(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi
    ): NewsRepository = NewsRepositoryImp(newsApi)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository)
        )
    }
}