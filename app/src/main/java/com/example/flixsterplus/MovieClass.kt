package com.example.flixsterplus

import com.google.gson.annotations.SerializedName

class MovieClass {

    @JvmField
    @SerializedName("name")
    var title: String? = null

    @JvmField
    @SerializedName("profile_path")
    var MovieImageUrl: String ?= null


    @JvmField
    @SerializedName("overview")
    var description: String? = null
}