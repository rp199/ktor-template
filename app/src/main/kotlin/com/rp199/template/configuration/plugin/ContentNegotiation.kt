package com.rp199.template.configuration.plugin

import io.ktor.http.ContentType
import io.ktor.serialization.ContentConverter
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation

fun Application.configureContentNegotiation(converter: ContentConverter) {
    install(ContentNegotiation) {
        register(ContentType.Application.Json, converter)
    }
}