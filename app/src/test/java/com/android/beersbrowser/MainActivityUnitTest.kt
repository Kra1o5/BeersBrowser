package com.android.beersbrowser

import com.android.beersbrowser.model.BeerInfo
import com.google.gson.Gson
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test

class MainActivityUnitTest {
    private var gson: Gson? = null

    @Before
    fun init() {
        gson = Gson()
    }

    @Test
    fun parse_Empty_List() {
        val readBeerList: List<BeerInfo> =
            gson!!.fromJson(EMPTY_LIST_BEER, Array<BeerInfo>::class.java).toList()
        assertNotNull(readBeerList)
        val jsonBeerList = gson!!.toJson(readBeerList)
        assertEquals(EMPTY_LIST_BEER, jsonBeerList)
    }

    @Test
    fun parse_Dummy_List() {
        val readBeerList: List<BeerInfo> =
            gson!!.fromJson(CHECK_LIST_BEER, Array<BeerInfo>::class.java)
                .toList()
        assertNotNull(readBeerList)
        val jsonBeerList = gson!!.toJson(readBeerList)
        assertEquals(CHECK_LIST_BEER, jsonBeerList)
    }

    companion object {
        const val EMPTY_LIST_BEER = "[]"
        const val CHECK_LIST_BEER =
            "[{\"name\":\"Brewdog Vs Beavertown\",\"description\":\"Loaded with roasty coffee notes and balanced with complex tobacco character. With smoky flavours from the malt, our Beavertown Collab is barrel-aged, but we don’t know for how long until its ready!\",\"image_url\":\"https://images.punkapi.com/v2/227.png\",\"abv\":9.2}]"
    }

}