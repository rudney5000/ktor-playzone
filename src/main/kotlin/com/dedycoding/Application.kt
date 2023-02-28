package com.dedycoding

import com.dedycoding.features.games.configureGamesRouting
import com.dedycoding.features.login.configureLoginRouting
import com.dedycoding.features.register.configureRegisterRouting
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.cio.*
import com.dedycoding.plugins.*
import org.jetbrains.exposed.sql.Database

fun main() {

    Database.connect(
        "jdbc:postgresql://localhost:5432/playzone",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "admin"
    )

    embeddedServer(CIO, port = 8000, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureLoginRouting()
    configureRegisterRouting()
    configureGamesRouting()
    configureRouting()
}
