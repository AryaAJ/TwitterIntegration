package com.android.example.flow.twitter.data.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.example.flow.twitter.data.models.User

/**
Created by AjayArya on 2020-08-16
 */
/**
 *      In this class methods are defined to access database
 *
 */
@Dao
interface UsersDao {

    @Query("SELECT * FROM users")
    fun getUsersList(): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUsersList(users: List<User>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUsersDetail(user: User)

}