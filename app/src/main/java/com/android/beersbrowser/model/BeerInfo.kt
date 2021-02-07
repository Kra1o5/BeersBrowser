package com.android.beersbrowser.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class BeerInfo : Serializable {
    @SerializedName("name")
    var name: String? = null
    @SerializedName("description")
    var description: String? = null
    @SerializedName("image_url")
    var imageURL: String? = null
    @SerializedName("abv")
    var abv: Double? = null
}