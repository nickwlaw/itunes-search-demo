package com.nickwlaw.itunessearchdemo.data

import com.nickwlaw.itunessearchdemo.domain.Song
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ITunesSearchResponse(
    val resultCount: Int,
    val results: List<SongResponse>
) {
    data class SongResponse(
        val wrapperType: String,
        val explicitness: String,
        val kind: String,
        val trackName: String,
        val artistName: String,
        val collectionName: String,
        val censoredName: String?,
        val artistViewUrl: String?,
        val collectionViewUrl: String?,
        val trackViewUrl: String?,
        val artworkUrl100: String?,
        val artworkUrl60: String?,
        val previewUrl: String?,
        val trackTimeMillis: Long?
    ) {
        fun toDomain(): Song {
            return Song(
                trackName,
                artistName,
                collectionName,
                isExplicit = when (explicitness) {
                    "explicit" -> true
                    else -> false
                },
                censoredName,
                artistViewUrl,
                collectionViewUrl,
                trackViewUrl,
                artworkUrl100 ?: artworkUrl60,
                previewUrl,
                trackTimeMillis
            )
        }
    }
}