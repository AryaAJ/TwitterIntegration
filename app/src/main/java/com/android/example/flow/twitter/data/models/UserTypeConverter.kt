package com.android.example.flow.twitter.data.models

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
Created by AjayArya on 2020-08-16
 */

/**
 *      It is used to store custom objects in the database
 *     It holds all converters required to convert VenueList API response object to database type
 *
 */
class UserTypeConverter {

    @TypeConverter
    fun fromString(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayListToString(list: List<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun stringToUser(json: String): User {
        val gson = Gson()
        val type = object : TypeToken<User>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun UserToString(list: User): String {
        val gson = Gson()
        val type = object : TypeToken<User>() {}.type
        return gson.toJson(list, type)
    }

    @TypeConverter
    fun stringToTweets(json: String): Tweet {
        val gson = Gson()
        val type = object : TypeToken<User>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun TweetsToString(list: Tweet): String {
        val gson = Gson()
        val type = object : TypeToken<Tweet>() {}.type
        return gson.toJson(list, type)
    }
}