package com.android.example.flow.twitter.data.models

/**
 * @property exception  is typically null when there is data
 * @property data       is the data thrown by the API
 */
class ResponseWrapper<T, E : Exception?>(var data: T, var exception: E)