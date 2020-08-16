package com.android.example.flow.twitter.network.services

import com.android.example.flow.twitter.data.models.User
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Headers
import retrofit2.http.QueryMap


/**
 * Created by AjayArya on 2020-08-16
 */
interface UserSearchService {
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @GET("/1.1/users/search.json")
    suspend fun getUsers(@HeaderMap hMap: HashMap<String, String>, @QueryMap query: HashMap<String, String>): List<User>
}