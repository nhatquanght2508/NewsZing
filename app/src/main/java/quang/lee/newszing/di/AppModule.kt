package quang.lee.newszing.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import quang.lee.newszing.data.manger.LocalUserManagerImp
import quang.lee.newszing.domain.manager.LocalUserManager
import quang.lee.newszing.domain.usecases.app_entry.AppEntryUseCases
import quang.lee.newszing.domain.usecases.app_entry.ReadAppEntry
import quang.lee.newszing.domain.usecases.app_entry.SaveAppEntry
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
}