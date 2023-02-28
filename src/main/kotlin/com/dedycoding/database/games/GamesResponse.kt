package com.dedycoding.database.games

import kotlinx.serialization.Serializable

@Serializable
data class GamesResponse(
    val gameId: String,
    val title: String,
    val description: String,
    val version: String,
    val size: String
)