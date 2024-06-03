package com.example.tmdb_api.domain.models

data class APIResponse (
    val shows: List<Show>,
    val hasMore: Boolean,
    val nextCursor: String
)

data class Show (
    val itemType: String,
    val showType: String,
    val id: String,
    val imdbID: String?,
    val tmdbID: String?,
    val title: String?,
    val overview: String?,
    val releaseYear: Long? = null,
    val originalTitle: String?,
    val genres: List<Genre>?,
    val directors: List<String>? = null,
    val cast: List<String>?,
    val rating: Long?,
    val imageSet: ShowImageSet?,
    val streamingOptions: StreamingOptions?,
    val firstAirYear: Long? = null,
    val lastAirYear: Long? = null,
    val creators: List<String>? = null,
    val seasonCount: Long? = null,
    val episodeCount: Long? = null
)

data class Genre (
    val id: String,
    val name: String
)

data class ShowImageSet (
    val verticalPoster: Vertical,
    val horizontalPoster: Horizontal,
    val verticalBackdrop: VerticalBackdrop?,
    val horizontalBackdrop: HorizontalBackdrop?,

)

data class HorizontalBackdrop (
    val w360: String,
    val w480: String,
    val w720: String,
    val w1080: String,
    val w1440: String
)

data class VerticalBackdrop (
    val w240: String,
    val w360: String,
    val w480: String,
    val w600: String,
    val w720: String
)

data class Horizontal (
    val w360: String,
    val w480: String,
    val w720: String,
    val w1080: String,
    val w1440: String
)

data class Vertical (
    val w240: String,
    val w360: String,
    val w480: String,
    val w600: String,
    val w720: String
)


enum class ShowType {
    Movie,
    Series
}

data class StreamingOptions (
    val us: List<Me>? = null
)

data class Me (
    val service: Addon,
    val type: String,
    val link: String,
    val videoLink: String? = null,
    val quality: String? = null,
    val audios: List<Audio>,
    val subtitles: List<Subtitle>,
    val expiresSoon: Boolean,
    val availableSince: Long,
    val addon: Addon? = null,
    val price: Price? = null,
    val expiresOn: Long? = null
)

data class Addon (
    val id: String,
    val name: String,
    val homePage: String,
    val themeColorCode: String,
    val imageSet: AddonImageSet
)

data class AddonImageSet (
    val lightThemeImage: String,
    val darkThemeImage: String,
    val whiteImage: String
)

data class Audio (
    val language: String,
    val region: String? = null
)

data class Price (
    val amount: String,
    val currency: String,
    val formatted: String
)

enum class Currency {
    Usd
}

enum class Quality {
    HD,
    SD,
    Uhd
}

data class Subtitle (
    val closedCaptions: Boolean,
    val locale: Audio
)
