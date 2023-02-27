package com.dedycoding.features.login

import com.dedycoding.cache.InMemoryCache
import com.dedycoding.database.tokens.TokenDTO
import com.dedycoding.database.tokens.Tokens
import com.dedycoding.database.users.Users
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.*

class LoginController(
    private val call: ApplicationCall
) {
    suspend fun performLogin(){
        val receive = call.receive<LoginReceiveRemote>()
        val userDTO = Users.fetchUser(receive.login)

        if (userDTO == null){
            call.respond(HttpStatusCode.BadRequest, "User not found")
        }else{
            if (userDTO.password == receive.password){
                val token = UUID.randomUUID().toString()
                Tokens.insert(
                    TokenDTO(
                        rowID = UUID.randomUUID().toString(),
                        login = receive.login,
                        token = token
                    )
                )
                call.respond(LoginResponseRemote(token = token))
            }else{
                call.respond(HttpStatusCode.BadRequest, "Invalid password")
            }
        }
    }
}