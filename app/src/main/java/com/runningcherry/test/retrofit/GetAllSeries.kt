package com.runningcherry.test.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface GetAllSeries {
    @GET("/shows/210/episodes")
    fun getAllSeries () : Call<List<SeriesItem>>
}