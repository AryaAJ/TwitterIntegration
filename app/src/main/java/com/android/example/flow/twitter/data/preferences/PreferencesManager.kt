package com.android.example.flow.twitter.data.preferences

import android.content.Context
import android.content.SharedPreferences
import com.android.example.flow.twitter.Constants


/**
 * Created by AjayArya on 2020-08-16
 */
class PreferencesManager(context: Context) {
    private val sharedPreferencesManager: SharedPreferences =
        context.getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE)
    private val editor = sharedPreferencesManager.edit()

    fun storeQuery(query: String) {
        val storedQueries = mutableSetOf<String>()
        storedQueries.apply {
            addAll(getAllQueries())
            add(query)
        }
        editor.putStringSet(Constants.QUERIES, storedQueries).apply()
    }

    fun getAllQueries(): MutableSet<String> =
        sharedPreferencesManager.getStringSet(Constants.QUERIES, mutableSetOf())!!
}