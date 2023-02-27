package com.dedycoding

import com.dedycoding.features.login.configureLoginRouting
import com.dedycoding.features.register.configureRegisterRouting
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.cio.*
import com.dedycoding.plugins.*

fun main() {
    embeddedServer(CIO, port = 8000, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureLoginRouting()
    configureRegisterRouting()
    configureRouting()
}
