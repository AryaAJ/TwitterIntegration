package com.android.example.flow.twitter.di

import com.android.example.flow.twitter.network.services.TweetsService
import com.android.example.flow.twitter.network.services.UserSearchService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://api.twitter.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
    }

    @Singleton
    @Provides
    fun providesClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client: OkHttpClient
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(30, TimeUnit.SECONDS)
        builder.readTimeout(30, TimeUnit.SECONDS)
        builder.writeTimeout(30, TimeUnit.SECONDS)
        builder.addInterceptor(interceptor)
        client = builder.build()
        return client
    }


    @Singleton
    @Provides
    fun provideTweetService(retrofit: Retrofit.Builder): TweetsService {
        return retrofit
            .build()
            .create(TweetsService::class.java)
    }

    @Singleton
    @Provides
    fun provideUserService(retrofit: Retrofit.Builder): UserSearchService {
        return retrofit
            .build()
            .create(UserSearchService::class.java)
    }

}




















