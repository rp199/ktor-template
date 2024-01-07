package com.rp199.template.routes

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get

fun Route.healthCheck() {
    get("/.well-known/healthcheck") {
        call.respond(HttpStatusCode.OK, "OK")
    }
}