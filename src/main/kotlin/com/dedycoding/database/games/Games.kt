package com.dedycoding.database.games

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object Games: Table() {
    private val gameID = Games.varchar("gameID", 100)
    private val name = Games.varchar("name", 100)
    private val backdrop = Games.varchar("backdrop", 50).nullable()
    private val logo = Games.varchar("logo", 50)
    private val description = Games.varchar("description", 100)
    private val downloadCount = Games.integer("download_count")
    private val version = Games.varchar("version", 50)
    private val weight = Games.varchar("weight", 50)

    fun insert(gamesDTO: GamesDTO){
        transaction {
            Games.insert{
                it[gameID] = gamesDTO.gameID
                it[name] = gamesDTO.name
                it[backdrop] = gamesDTO.backdrop
                it[logo] = gamesDTO.logo
                it[description] = gamesDTO.description
                it[downloadCount] = gamesDTO.downloadCount
                it[version] = gamesDTO.version
                it[weight] = gamesDTO.size
            }
        }
    }

    fun fetchTokens(): List<GamesDTO> {
        return try {
            transaction {
                Games.selectAll().toList()
                    .map {
                        GamesDTO(
                            gameID = it[gameID],
                            name = it[name],
                            backdrop = it[backdrop],
                            logo = it[logo],
                            description = it[description],
                            downloadCount = it[downloadCount],
                            version = it[version],
                            size = it[weight],
                        )
                    }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}