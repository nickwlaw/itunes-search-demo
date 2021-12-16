package com.nickwlaw.itunessearchdemo.data

import retrofit2.Response
import retrofit2.http.GET

interface ITunesApi {
    @GET("search")
    suspend fun getITunesSearchResult(): Response<ITunesSearchResponse>
}