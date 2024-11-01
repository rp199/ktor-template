package com.rp199.template.configuration.plugin

import com.rp199.template.routes.healthCheck
import com.rp199.template.routes.sampleRoute
import io.ktor.server.application.Application
import io.ktor.server.routing.routing
import org.koin.ktor.ext.get

fun Application.configureRoutes() {
    routing {
        healthCheck()
        sampleRoute(sampleService = get())
    }
}