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
@Entity(tableName = "users")
@Parcelize
class User(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    @SerializedName("id") var id: String,
    @ColumnInfo(name = "name")
    @SerializedName("name") var name: String,
    @ColumnInfo(name = "screenName")
    @SerializedName("screen_name") var screenName: String,
    @ColumnInfo(name = "followersCount")
    @SerializedName("followers_count") var followersCount: String,
    @ColumnInfo(name = "profileBanner")
    @SerializedName("profile_banner_url") var profileBanner: String,
    @ColumnInfo(name = "profileImage")
    @SerializedName("profile_image_url_https") var profileImage: String
) : Parcelable
