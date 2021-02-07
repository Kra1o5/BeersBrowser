package com.android.beersbrowser.model

import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {
    @GET("beers")
    fun getAllBeers(): Call<MutableList<BeerInfo>>
}