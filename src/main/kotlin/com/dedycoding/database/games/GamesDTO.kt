package com.dedycoding.database.games

import com.dedycoding.features.games.models.CreateGamesResponse

data class GamesDTO(
    val gameID: String,
    val name: String,
    val backdrop: String?,
    val logo: String,
    val description: String,
    val downloadCount: Int,
    val version: String,
    val size: String,
)


fun GamesDTO.mapToCreateGamesResponse(): CreateGamesResponse =
    CreateGamesResponse(
        gameID = gameID,
        title = name,
        description = description,
        version = version,
        size = size
    )