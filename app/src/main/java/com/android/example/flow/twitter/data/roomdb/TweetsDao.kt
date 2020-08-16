package com.android.example.flow.twitter.data.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.example.flow.twitter.data.models.Tweet

/**
Created by AjayArya on 2020-08-16
 *
 *      In this class methods are defined to access database
 *
 */
@Dao
interface TweetsDao {

    @Query("SELECT * FROM tweets where user_id IN (:id)")
    fun getTweetsList(id: String): List<Tweet>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTweetsList(tweets: List<Tweet>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTweetsDetail(tweet: Tweet)

}