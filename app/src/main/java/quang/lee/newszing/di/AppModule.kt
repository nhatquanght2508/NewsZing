package quang.lee.newszing.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import quang.lee.newszing.data.local.NewsDao
import quang.lee.newszing.data.local.NewsDatabase
import quang.lee.newszing.data.local.NewsTypeConvertor
import quang.lee.newszing.data.manger.LocalUserManagerImp
import quang.lee.newszing.data.remote.NewsApi
import quang.lee.newszing.data.remote.repository.NewsRepositoryImp
import quang.lee.newszing.domain.manager.LocalUserManager
import quang.lee.newszing.domain.repository.NewsRepository
import quang.lee.newszing.domain.usecases.app_entry.AppEntryUseCases
import quang.lee.newszing.domain.usecases.app_entry.ReadAppEntry
import quang.lee.newszing.domain.usecases.app_entry.SaveAppEntry
import quang.lee.newszing.domain.usecases.news.DeleteArticle
import quang.lee.newszing.domain.usecases.news.GetArticles
import quang.lee.newszing.domain.usecases.news.GetNews
import quang.lee.newszing.domain.usecases.news.NewsUseCases
import quang.lee.newszing.domain.usecases.news.SearchNews
import quang.lee.newszing.domain.usecases.news.UpsertArticle
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
        newsRepository: NewsRepository,
        newsDao: NewsDao
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsDao),
            deleteArticle = DeleteArticle(newsDao),
            getArticles = GetArticles(newsDao)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ) : NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = "news_db"
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ) : NewsDao = newsDatabase.newsDao
}