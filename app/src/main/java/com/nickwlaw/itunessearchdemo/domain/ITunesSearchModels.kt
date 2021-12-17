package com.nickwlaw.itunessearchdemo.domain

data class ITunesSearchQuery(
    val term: String,
    val country: CountryCode,
    val optionalParameters: Map<OptionalParams, String>
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

enum class OptionalParams(key: String) {
    MEDIA("media"),
    ENTITY("entity"),
    ATTRIBUTE("attribute"),
    LIMIT("limit"),
    EXPLICIT("explicit")
}

data class Song(
    val trackName: String,
    val artist: String,
    val album: String,
    val isExplicit: Boolean,
    val censoredName: String,
    val iTunesUrl: String?,
    val previewUrl: String?,
    val duration: Long?
)

// TODO("add models for artist, album, etc.")
