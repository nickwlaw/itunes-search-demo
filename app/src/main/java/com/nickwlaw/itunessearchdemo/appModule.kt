package com.nickwlaw.itunessearchdemo

import com.nickwlaw.itunessearchdemo.data.ITunesApi
import com.nickwlaw.itunessearchdemo.data.ITunesApiBuilder
import com.nickwlaw.itunessearchdemo.domain.ITunesRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    // resources singleton
    single { androidContext().resources }

    // api singleton
    single { ITunesApiBuilder.buildApi(ITunesApi::class.java) }

    // repository singleton
    single { ITunesRepository(get()) }
}