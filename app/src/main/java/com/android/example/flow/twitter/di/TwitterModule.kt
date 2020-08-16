package com.android.example.flow.twitter.di

import android.content.Context
import android.util.Log
import com.android.example.flow.twitter.Constants
import com.twitter.sdk.android.core.DefaultLogger
import com.twitter.sdk.android.core.TwitterAuthConfig
import com.twitter.sdk.android.core.TwitterConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object TwitterModule {

    @Singleton
    @Provides
    fun provideTwitterBuilder(@ApplicationContext context: Context): TwitterConfig {
        return TwitterConfig.Builder(context)
            .logger(DefaultLogger(Log.DEBUG))
            .twitterAuthConfig(
                TwitterAuthConfig(
                    Constants.consumerKey,
                    Constants.consumerSecret
                )
            )
            .debug(true)
            .build()
    }
}