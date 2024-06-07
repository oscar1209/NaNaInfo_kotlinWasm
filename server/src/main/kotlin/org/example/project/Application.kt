package org.example.project

import Greeting
import SERVER_PORT
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*



fun main() {
    var a: Int = 0
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    routing {
       // staticResources("/static", "files")
       // staticResources("/app", "packages/composeApp/kotlin")
        get("/") {

            //call.respondText("Ktor: ${Greeting().greet()}")

        }
    }
}