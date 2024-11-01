package com.rp199.template.configuration.plugin

import com.rp199.template.routes.healthCheck
import com.rp199.template.routes.sampleRoute
import com.rp199.template.service.SampleService
import io.ktor.server.application.Application
import io.ktor.server.routing.routing
import org.koin.ktor.ext.inject

fun Application.configureRoutes() {
    // cannot inject on the routes anymore https://github.com/InsertKoinIO/koin/issues/1716
    val sampleService by inject<SampleService>()
    routing {
        healthCheck()
        sampleRoute(sampleService = sampleService)
    }
}