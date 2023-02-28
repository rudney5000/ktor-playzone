package com.dedycoding.features.games.models

import kotlinx.serialization.Serializable

@Serializable
data class FetchGamesResponse(
    val games: List<CreateGamesResponse>
)