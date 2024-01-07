package com.rp199.template.routes

import com.rp199.template.service.SampleService
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get

fun Route.sampleRoute(sampleService: SampleService) {
    get("/test") {
        call.respond(sampleService.doStuff())
    }
}