package com.android.example.flow.twitter

import android.util.Base64
import java.util.concurrent.TimeUnit


/**
 * Created by AjayArya on 2020-08-16
 */
class Constants {
    companion object {
        const val SCREEN_NAME = "screenName"
        const val ID = "id"
        const val QUERIES: String = "queriesSet"
        const val PREFS: String = "twitterPrefs"

//        val consumerKey = "nDlWnChz9pVSZBgLsL6JTuEkz"
//        val accessToken = "1195684525637955584-qBIvzaRjwaxhZUhwh8De1NU9Vv1kDR"
//        val consumerSecret = "EU640BporNu4NaZWInU28vsq5fpcuxlTw3iKtX7i7lEPJtWW3f"
//        val authTokenSecret = "ivp6qmKDtjB7050JEzJLY8COKKD9He8fKJPfPu78JkD2H"

        val consumerKey = "CnljtZvqdrbLtKiZZWMMvRRpn"
        val accessToken = "923498754015092736-KFEq4mXsp8BGSErYOxmvgSHhDvvIokd"
        val consumerSecret = "7H53nYbriD6CXLRC9IElAFzL5lZzadspgCZtNgIFLGNjGOxy1Y"
        val authTokenSecret = "OM4ERRkbeEPV7LxSXStiIEVHXltxoHDPn9EyMGf16OJLY"

//        val apiKey = "CnljtZvqdrbLtKiZZWMMvRRpn"
//        val apiSecret = "7H53nYbriD6CXLRC9IElAFzL5lZzadspgCZtNgIFLGNjGOxy1Y"
//        val bearerToken = "AAAAAAAAAAAAAAAAAAAAACZdGwEAAAAAGc%2F7xitfRY7umupLB0nP2ttGrG4%3D03PwrBERtnhPjjq6OmpfptsL39uKJ6zIRKodSOuEWxqancbLaU"

        val TwitterHeader = "OAuth " +
                "oauth_consumer_key=\"$consumerKey\"" + ", " +
                "oauth_nonce=\"{nonce}\"" + ", " +
                "oauth_signature=\"{signature}\"" + ", " +
                "oauth_signature_method=\"HMAC-SHA1\"" + ", " +
                "oauth_timestamp=\"{timestamp}\"" + ", " +
                "oauth_token=\"$accessToken\"" + ", " +
                "oauth_version=\"1.0\""

        const val TwitterParams = "oauth_consumer_key=nDlWnChz9pVSZBgLsL6JTuEkz" + "&" +
                "oauth_nonce={nonce}" + "&" +
                "oauth_signature_method=HMAC-SHA1" + "&" +
                "oauth_timestamp={timestamp}" + "&" +
                "oauth_token=1195684525637955584-qBIvzaRjwaxhZUhwh8De1NU9Vv1kDR" + "&" +
                "oauth_version=1.0"

        val URL = "https://api.twitter.com/1.1/users/search.json"

        fun generateNonce(timeStamp: String): String {
            return String(
                Base64.encode(
                    ("nDlWnChz9pVSZBgLsL6JTuEkz:$timeStamp").toByteArray(),
                    Base64.DEFAULT
                )
            ).trim()
        }

        fun generateTimeStamp(): String =
            TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()).toString()
    }
}