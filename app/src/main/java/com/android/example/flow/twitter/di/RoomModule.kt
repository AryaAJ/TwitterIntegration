package com.android.example.flow.twitter.di

import android.content.Context
import androidx.room.Room
import com.android.example.flow.twitter.data.preferences.PreferencesManager
import com.android.example.flow.twitter.data.roomdb.TwitterDatabase
import com.android.example.flow.twitter.data.roomdb.TweetsDao
import com.android.example.flow.twitter.data.roomdb.UsersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun provideTwitterDb(@ApplicationContext context: Context): TwitterDatabase {
        return Room
            .databaseBuilder(
                context,
                TwitterDatabase::class.java,
                TwitterDatabase.DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun provideTwitterDAO(twitterDatabase: TwitterDatabase): TweetsDao {
        return twitterDatabase.tweetsDao
    }

    @Singleton
    @Provides
    fun provideUsersDAO(twitterDatabase: TwitterDatabase): UsersDao {
        return twitterDatabase.usersDao
    }

    @Singleton
    @Provides
    fun providePreferenceManager(@ApplicationContext context: Context): PreferencesManager {
        return PreferencesManager(context)
    }
}