package com.dedycoding.database.games

import com.dedycoding.features.games.models.CreateGamesRequest
import java.util.*

fun CreateGamesRequest.mapToGamesDTO(): GamesDTO =
    GamesDTO(
        gameID = UUID.randomUUID().toString(),
        name = title,
        description = description,
        version = version,
        size = size,
        backdrop = "",
        logo = "",
        downloadCount = 0
    )