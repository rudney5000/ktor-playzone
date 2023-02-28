package com.dedycoding.features.games

import com.dedycoding.database.games.Games
import com.dedycoding.database.games.mapToCreateGamesResponse
import com.dedycoding.database.games.mapToGamesDTO
import com.dedycoding.features.games.models.CreateGamesRequest
import com.dedycoding.features.games.models.FetchGamesRequest
import com.dedycoding.features.games.models.FetchGamesResponse
import com.dedycoding.utils.TokenCheck
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class GamesController(
    private val call: ApplicationCall
) {

    suspend fun performSearch() {
        val request = call.receive<FetchGamesRequest>()
        val token = call.request.headers["Bearer-Authorization"]

        if (TokenCheck.isTokenValid(token.orEmpty()) || TokenCheck.isTokenAdmin(token.orEmpty())) {
                call.respond(FetchGamesResponse(
                    games = Games.fetchTokens().filter { it.name.contains(request.searchQuery, ignoreCase = true) }
                        .map { it.mapToCreateGamesResponse() }
                ))
        } else {
            call.respond(HttpStatusCode.Unauthorized, "Token expired")
        }
    }

    suspend fun createGame() {
        val token = call.request.headers["Bearer-Authorization"]
        if (TokenCheck.isTokenAdmin(token.orEmpty())) {
            val request = call.receive<CreateGamesRequest>()
            val game = request.mapToGamesDTO()
            Games.insert(game)
            call.respond(game.mapToCreateGamesResponse())
        } else {
            call.respond(HttpStatusCode.Unauthorized, "Token expired")
        }
    }
}
