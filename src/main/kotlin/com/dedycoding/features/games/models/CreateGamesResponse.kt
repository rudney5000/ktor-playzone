package com.dedycoding.features.games.models

import kotlinx.serialization.Serializable

@Serializable
data class CreateGamesResponse(
    val gameID: String,
    val title: String,
    val description: String,
    val version: String,
    val size: String
)