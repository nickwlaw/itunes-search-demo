package com.nickwlaw.itunessearchdemo.data

import com.nickwlaw.itunessearchdemo.domain.OptionalParams
import retrofit2.Response
import retrofit2.http.FieldMap
import retrofit2.http.GET
import retrofit2.http.Query

interface ITunesApi {
    @GET("search")
    suspend fun getITunesSearchResult(
        @Query("term", encoded = true) term: String,
        @Query("country") countryCode: String,
        @FieldMap optionalParams: Map<OptionalParams, String>?
    ): Response<ITunesSearchResponse>
}