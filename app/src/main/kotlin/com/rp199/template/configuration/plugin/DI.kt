package com.rp199.template.configuration.plugin

import com.rp199.template.configuration.modules.serialization
import com.rp199.template.configuration.modules.services
import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.ktor.plugin.Koin

fun Application.installKoin() {
    install(Koin) {
        modules(services, serialization)
    }
}