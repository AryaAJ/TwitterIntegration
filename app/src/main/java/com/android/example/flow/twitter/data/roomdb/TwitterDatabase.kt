package com.android.example.flow.twitter.data.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.example.flow.twitter.data.models.Tweet
import com.android.example.flow.twitter.data.models.User
import com.android.example.flow.twitter.data.models.UserTypeConverter

/**
Created by AjayArya on 2020-08-16
 */

@Database(
    entities = [User::class, Tweet::class], version = 1, exportSchema = false
)
@TypeConverters(
    UserTypeConverter::class
)
abstract class TwitterDatabase : RoomDatabase() {
    abstract val tweetsDao: TweetsDao
    abstract val usersDao: UsersDao

    companion object {
        val DATABASE_NAME: String = "Twitter_db"
    }
}