package com.example.flixsterplus

import com.google.gson.annotations.SerializedName

data class PersonClass(
    @SerializedName("name")
    var name: String? = null,

    @SerializedName("profile_path")
    var faceImage: String? = null,

    @SerializedName("known_for")
    var knownFor: List<KnownFor>? = null // Change this to a list of KnownFor objects
)

data class KnownFor(
    @SerializedName("title")
    var title: String? = null,

    @SerializedName("poster_path")
    var posterPath: String? = null,

    @SerializedName("overview")
    var overview: String? = null
)



/**
best-known work, poster, and description:
 */

