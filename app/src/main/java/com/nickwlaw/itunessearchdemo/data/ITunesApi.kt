package com.nickwlaw.itunessearchdemo.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ITunesApi {
    @GET("search")
    suspend fun getITunesSearchResult(
        @Query("term", encoded = true) term: String,
        @Query("country") countryCode: String,
        @Query("media") media: String?,
        @Query("entity") entity: String?,
        @Query("attribute") attribute: String?,
        @Query("limit") limit: Int?,
        @Query("explicit") explicit: String?
    ): Response<ITunesSearchResponse>
}