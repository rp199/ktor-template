package com.rp199.template.configuration.plugin

import com.rp199.template.configuration.modules.installSerialization
import com.rp199.template.configuration.modules.installServices
import io.ktor.server.application.Application
import io.ktor.server.plugins.di.dependencies

fun Application.configureKoin() {
    dependencies {
        installServices()
        installSerialization()
    }
}