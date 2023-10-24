package com.example.wordcraftsmen.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.wordcraftsmen.data.PrepopulateRoomCallback
import com.example.wordcraftsmen.data.WordDao
import com.example.wordcraftsmen.data.WordDatabase
import com.example.wordcraftsmen.domain.WordRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideWordDatabase(
        application: Application,
        provider: Provider<WordDao>
    ) =
        Room.databaseBuilder(
            application.applicationContext,
            WordDatabase::class.java,
            WordDatabase.DB_NAME
        ).addCallback(
            PrepopulateRoomCallback(
                application.applicationContext,
                provider
            )
        ).build()

    @Provides
    @Singleton
    fun provideWordsRepository(wordDao: WordDao) = WordRepository(wordDao)

    @Singleton
    @Provides
    fun providesWordsDao(database: WordDatabase) = database.noteDao()
}