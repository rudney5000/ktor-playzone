package com.dedycoding.features.register

import com.dedycoding.cache.InMemoryCache
import com.dedycoding.database.tokens.TokenDTO
import com.dedycoding.database.tokens.Tokens
import com.dedycoding.database.users.UserDTO
import com.dedycoding.database.users.Users
import com.dedycoding.features.token.TokenCache
import com.dedycoding.utils.isValidEmail
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.*

class RegisterController(val call: ApplicationCall) {

    suspend fun registerNewUser(){
        val registerReceiveRemote = call.receive<RegisterReceiveRemote>()
            if (!registerReceiveRemote.email.isValidEmail()){
                call.respond(HttpStatusCode.BadRequest, "Email is not valid")
            }

        val userDTO = Users.fetchUser(registerReceiveRemote.login)

        if (userDTO != null){
            call.respond(HttpStatusCode.Conflict, "User already exists")
        }else{
            val token = UUID.randomUUID().toString()
            Users.insert(
                UserDTO(
                    login = registerReceiveRemote.login,
                    password = registerReceiveRemote.password,
                    email = registerReceiveRemote.email,
                    username = ""
                )
            )

            Tokens.insert(
                TokenDTO(
                    rowID = UUID.randomUUID().toString(),
                    login = registerReceiveRemote.login,
                    token = token
                )
            )

            call.respond(RegisterResponseRemote(token = token))
        }
    }
}