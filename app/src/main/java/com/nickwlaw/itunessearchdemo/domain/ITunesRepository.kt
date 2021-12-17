package com.nickwlaw.itunessearchdemo.domain

import com.nickwlaw.itunessearchdemo.data.ITunesApi
import com.nickwlaw.itunessearchdemo.foundation.Either
import com.nickwlaw.itunessearchdemo.foundation.Failure


class ITunesRepository(private val api: ITunesApi) {

    val recentSearches: MutableList<ITunesSearchQuery> = mutableListOf()

    // TODO("update to more specific failure")
    suspend fun searchITunesForSong(query: ITunesSearchQuery): Either<Failure, List<Song>> {
        recentSearches.add(query)

        val response = api.getITunesSearchResult(
            query.term,
            query.country.isoCode,
            query.media,
            query.entity,
            query.attribute,
            query.limit,
            query.explicit
        )

        val results = mutableListOf<Song>()

        return if (response.isSuccessful) {
            response.body()?.results?.forEach {
                results.add(it.toDomain())
            }

            Either.Right(results)
        } else {
            Either.Left(Failure(response.message()))
        }
    }
}