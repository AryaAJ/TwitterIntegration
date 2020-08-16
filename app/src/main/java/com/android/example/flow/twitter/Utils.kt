package com.android.example.flow.twitter

import com.android.example.flow.twitter.parameters.OAuth1aParameters
import com.twitter.sdk.android.core.TwitterAuthConfig
import com.twitter.sdk.android.core.TwitterAuthToken
import com.twitter.sdk.android.core.models.Tweet

/**
 * Created by AjayArya on 2020-08-16
 */
fun getTwitterHeaderMap(queryMap: HashMap<String, String>): HashMap<String, String> {
    // generating the parameters of the API
    val hMap = HashMap<String, String>()

    // the authorization parameter is generated here
    hMap["authorization"] = OAuth1aParameters(
        TwitterAuthConfig(Constants.consumerKey, Constants.consumerSecret),
        TwitterAuthToken(Constants.accessToken, Constants.authTokenSecret), "", "GET",
        Constants.URL, queryMap
    ).authorizationHeader

    return hMap
}

fun prepareCustomTweetList(list: List<Tweet>): MutableList<com.android.example.flow.twitter.data.models.Tweet> {
    val mylist: MutableList<com.android.example.flow.twitter.data.models.Tweet> = arrayListOf()
    for (i in list) {
        val tweet = com.android.example.flow.twitter.data.models.Tweet(
            i.user.idStr,
            com.android.example.flow.twitter.data.models.User(
                i.user.id.toString(),
                i.user.name,
                i.user.screenName,
                i.user.followersCount.toString(),
                i.user.profileBannerUrl,
                i.user.profileImageUrlHttps
            )
            , i.text, i.retweetCount, i.favoriteCount, i.id.toString()
        )
        mylist.add(tweet)
    }
    return mylist
}