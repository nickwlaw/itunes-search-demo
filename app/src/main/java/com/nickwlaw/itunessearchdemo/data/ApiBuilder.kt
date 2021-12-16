package com.nickwlaw.itunessearchdemo.data

import com.nickwlaw.itunessearchdemo.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ITunesApiBuilder {
    private val httpClient = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.URL_ITUNES_API)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()

    fun <T> buildApi(api: Class<T>): T {
        return retrofit.create(api)
    }
}