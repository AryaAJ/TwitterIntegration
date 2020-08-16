package com.android.example.flow.twitter.data.repository

import android.content.Context
import com.android.example.flow.twitter.NetworkManager
import com.android.example.flow.twitter.data.models.ResponseWrapper
import com.android.example.flow.twitter.data.models.User
import com.android.example.flow.twitter.getTwitterHeaderMap
import com.android.example.flow.twitter.network.services.UserSearchService
import com.android.example.flow.twitter.data.roomdb.UsersDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


/**
 * Created by AjayArya on 2020-08-16
 */
class SearchUserRepository constructor(
    private val usersDao: UsersDao,
    private val _userSearchService: UserSearchService,
    private val context: Context
) {

    /**
     * This is a flow using suspend
     * @param query is the twitter alias
     */
    fun getFlowListUser(query: String): Flow<ResponseWrapper<out List<User>?, out Exception?>> =
        flow {
            try {
                val queryMap = hashMapOf(
                    "q" to query,
                    "count" to 10.toString()
                )

                if (NetworkManager.isOnline(context)) {
                    val users = ResponseWrapper(
                        _userSearchService.getUsers(
                            getTwitterHeaderMap(queryMap),
                            queryMap
                        ), null
                    )

                    //add to db
                    if (users.data.isNotEmpty()) {
                        usersDao.addUsersList(users.data)
                    }

                    emit(ResponseWrapper(usersDao.getUsersList(), null))
                } else {
                    emit(ResponseWrapper(usersDao.getUsersList(), null))
                }

            } catch (e: Exception) {
                emit(ResponseWrapper(null, e))
            }
        }
}