package com.dedycoding.features.games.models

import kotlinx.serialization.Serializable

@Serializable
data class CreateGamesRequest(
    val title: String,
    val description: String,
    val version: String,
    val size: String
)
