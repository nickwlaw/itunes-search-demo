package com.nickwlaw.itunessearchdemo.domain

data class ITunesSearchQuery(
    val term: String,
    val country: CountryCode,
    val media: String?,
    val entity: String?,
    val attribute: String?,
    val limit: Int?,
    val explicit: String?
)

data class ITunesSongSearchQuery (
    val term: String,
    val country: CountryCode,
    val media: String = "music",
    val entity: String = "musicTrack",
    val attribute: String = "songTerm",
    val limit: Int? = null,
    val explicit: String? = null
)

enum class CountryCode(val isoCode: String) {
    CANADA("CA"),
    GERMANY("DE"),
    SPAIN("ES"),
    FRANCE("FR"),
    GHANA("GH"),
    INDIA("IN"),
    ITALY("IT"),
    SOUTH_KOREA("KR"),
    MEXICO("MX"),
    NIGERIA("NG"),
    UNITED_STATES("US"),
    SOUTH_AFRICA("ZA")
}

data class Song(
    val trackName: String,
    val artist: String,
    val album: String,
    val isExplicit: Boolean,
    val censoredName: String?,
    val artistUrl: String?,
    val albumUrl: String?,
    val iTunesUrl: String?,
    val artworkUrl: String?,
    val previewUrl: String?,
    val duration: Long?
)

// TODO("add models for artist, album, etc.")
