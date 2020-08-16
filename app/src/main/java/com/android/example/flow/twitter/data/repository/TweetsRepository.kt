package com.android.example.flow.twitter.data.repository

import android.util.Log
import com.android.example.flow.twitter.data.models.ResponseWrapper
import com.android.example.flow.twitter.prepareCustomTweetList
import com.android.example.flow.twitter.data.roomdb.TweetsDao
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterCore
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.models.Tweet
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

/**
 * Created by AjayArya on 2020-08-16
 */
@ExperimentalCoroutinesApi
class TweetsRepository constructor(private val tweetsDao: TweetsDao) {

    /**
     * @param screenName  is the unique name of the user
     * @param count       is the count of tweets
     */
    fun getTweets(
        screenName: String,
        count: Int,
        id: String
    ): Flow<ResponseWrapper<out List<com.android.example.flow.twitter.data.models.Tweet>?, out Exception?>> =
        callbackFlow {

            val call = TwitterCore.getInstance().apiClient.statusesService.userTimeline(
                null, screenName, count,
                null, null, null, null, null, null
            )

            val callback = object : Callback<List<Tweet>>() {

                override fun success(result: Result<List<Tweet>>?) {

                    //add to db
                    if (result?.data?.isNotEmpty()!!)
                        tweetsDao.addTweetsList(prepareCustomTweetList(result.data))

                    offer(
                        ResponseWrapper(
                            tweetsDao.getTweetsList(id),
                            null
                        )
                    )
                }

                override fun failure(exception: TwitterException?) {
                    Log.e("error", exception?.message.toString())
                    val list = tweetsDao.getTweetsList(id)
                    if (list.isNotEmpty())
                        offer(ResponseWrapper(list, null))
                    else
                        offer(ResponseWrapper(null, exception))

                    close()
                }

            }
            call.enqueue(callback)
            awaitClose { }

        }
}