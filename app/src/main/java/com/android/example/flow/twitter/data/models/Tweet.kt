package com.android.example.flow.twitter.data.models

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by AjayArya on 2020-08-16
 */
@Entity(tableName = "tweets")
@Parcelize
class Tweet(
    @ColumnInfo(name = "user_id")
    @SerializedName("idStr") val idStr: String,
    @NonNull
    @ColumnInfo(name = "user")
    @SerializedName("user") val user: User,
    @ColumnInfo(name = "text")
    @SerializedName("text") val text: String,
    @ColumnInfo(name = "replyCount")
    @SerializedName("reply_count") val replyCount: Int,
    @ColumnInfo(name = "favoriteCount")
    @SerializedName("favorite_count") val favoriteCount: Int,
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: String
) : Parcelable