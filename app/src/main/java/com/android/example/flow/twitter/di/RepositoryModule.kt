package com.android.example.flow.twitter.di

import android.content.Context
import com.android.example.flow.twitter.data.repository.SearchUserRepository
import com.android.example.flow.twitter.data.repository.TweetsRepository
import com.android.example.flow.twitter.network.services.UserSearchService
import com.android.example.flow.twitter.data.roomdb.TweetsDao
import com.android.example.flow.twitter.data.roomdb.UsersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideTweetsRepository(
        blogDao: TweetsDao
    ): TweetsRepository {
        return TweetsRepository(blogDao)
    }

    @Singleton
    @Provides
    fun provideUsersRepository(
        usersDao: UsersDao,
        retrofit: UserSearchService,
        @ApplicationContext context: Context
    ): SearchUserRepository {
        return SearchUserRepository(usersDao, retrofit, context)
    }
}














