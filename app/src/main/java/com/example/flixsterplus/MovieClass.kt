package com.example.flixsterplus

import com.google.gson.annotations.SerializedName

class MovieClass {

    @JvmField
    @SerializedName("title")
    var title: String? = null

    @JvmField
    @SerializedName("poster_path")
    var MovieImageUrl: String ?= null


    @JvmField
    @SerializedName("overview")
    var description: String? = null
}