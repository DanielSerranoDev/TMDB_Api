package com.example.tmdb_api.domain.models

data class APIResponseChanges(
    val changes: List<Change>? = null,
    val shows: Map<String, Show>,
    val hasMore: Boolean? = null,
    val nextCursor: String? = null,
)

data class Change(
    val changeType: String? = null,
    val itemType: String? = null,
    val showID: String? = null,
    val showType: String? = null,
    val service: Addon? = null,
    val streamingOptionType: String? = null,
    val addon: Addon? = null,
    val timestamp: Long? = null,
    val link: String? = null,
)
