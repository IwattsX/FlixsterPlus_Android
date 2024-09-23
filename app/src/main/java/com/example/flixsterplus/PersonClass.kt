package com.example.flixsterplus

import android.os.Parcel
import android.os.Parcelable
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
    @SerializedName("title") val title: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("overview") val overview: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(posterPath)
        parcel.writeString(overview)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<KnownFor> {
        override fun createFromParcel(parcel: Parcel): KnownFor {
            return KnownFor(parcel)
        }

        override fun newArray(size: Int): Array<KnownFor?> {
            return arrayOfNulls(size)
        }
    }
}



/**
best-known work, poster, and description:
 */

