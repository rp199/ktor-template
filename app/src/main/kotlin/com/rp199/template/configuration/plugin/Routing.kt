package com.rp199.template.configuration.plugin

import com.rp199.template.routes.healthCheck
import com.rp199.template.routes.sampleRoute
import com.rp199.template.service.SampleService
import io.ktor.server.application.Application
import io.ktor.server.plugins.di.dependencies
import io.ktor.server.routing.routing

suspend fun Application.configureRoutes() {

    val sampleService = dependencies.resolve<SampleService>()
    routing {
        healthCheck()
        sampleRoute(sampleService = sampleService)
    }
}