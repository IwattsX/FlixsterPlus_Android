package com.example.flixsterplus

import com.google.gson.annotations.SerializedName

class PersonClass {

    @JvmField
    @SerializedName("id")
    var id: Int = 0

    @JvmField
    @SerializedName("name")
    var name: String? = null

    @JvmField
    @SerializedName("profile_path")
    var faceImage: String? = null

    @JvmField
    @SerializedName("known_for")
    var knownFor: List<MovieClass>? = null // Use MovieClass instead of String
}

class MovieClass {
    @JvmField
    @SerializedName("title")
    var title: String? = null

    @JvmField
    @SerializedName("poster_path")
    var posterImage: String? = null

    @JvmField
    @SerializedName("overview")
    var description: String? = null
}


/**
best-known work, poster, and description:
 */

