package com.example.tmdb_api.domain.models

data class APIResponseChanges (
    val changes: List<Change>? = null,
    val shows: Map<String, Show>,
    val hasMore: Boolean? = null,
    val nextCursor: String? = null
)

data class Change (
    val changeType: ChangeType? = null,
    val itemType: ItemType? = null,
    val showID: String? = null,
    val showType: ShowType? = null,
    val service: Addon? = null,
    val streamingOptionType: Type? = null,
    val addon: Addon? = null,
    val timestamp: Long? = null,
    val link: String? = null
)


enum class ChangeType {
    New
}

enum class ItemType {
    Show
}


