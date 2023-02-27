package com.dedycoding.database.tokens

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object Tokens: Table() {
    private val id = Tokens.varchar("id", 50)
    private val login = Tokens.varchar("login", 50)
    private val token = Tokens.varchar("token", 50)

    fun insert(tokenDTO: TokenDTO){
            transaction {
                Tokens.insert{
                    it[id] = tokenDTO.rowID
                    it[login] = tokenDTO.login
                    it[token] = tokenDTO.token
                }
            }
    }

    fun fetchTokens(): List<TokenDTO> {
        return try {
            transaction {
                Tokens.selectAll().toList()
                    .map {
                        TokenDTO(
                            rowID = it[Tokens.id],
                            token = it[Tokens.token],
                            login = it[Tokens.login]
                        )
                    }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}